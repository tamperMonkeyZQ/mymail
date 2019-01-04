package com.tamper.mymail.controller;

import com.tamper.mymail.dao.HmAccountsMapper;
import com.tamper.mymail.models.HmAccounts;
import com.tamper.mymail.models.Mail;
import com.tamper.mymail.models.SimpleMail;
import com.tamper.mymail.services.MD5Service;
import com.tamper.mymail.services.OhMyEmail;
import com.tamper.mymail.services.POP3ReceiveMail;
import com.tamper.mymail.services.SendMailException;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;

import static com.tamper.mymail.services.OhMyEmail.SMTP_ZQ;

@Controller
public class IndexController {
    @Autowired
    HmAccountsMapper hmAccountsMapper;
    @Autowired
    POP3ReceiveMail pop3ReceiveMail;
    //TODO
    @RequestMapping(value = "/mail", method = RequestMethod.GET)
    public String login(){
        return "login";
    }
    @RequestMapping(value = "/mail/loginCheck", method = RequestMethod.POST)
    public @ResponseBody Object loginCheck(HttpServletRequest httpServletRequest,
                                           @Param("username")String username,
                                           @Param("password")String password) throws UnsupportedEncodingException {
        HmAccounts hmAccounts = hmAccountsMapper.selectByAddress(username);
        HashMap<String, String> res = new HashMap<String, String>();
        res.put("stateCode", "2");
        if(hmAccounts==null){
            res.put("stateCode", "0");
        }
        else if(!hmAccounts.getAccountpassword().equals(MD5Service.Encrypt(password))){
            res.put("stateCode", "1");
        }
        hmAccounts.setAccountpassword(password);
        httpServletRequest.getSession().setAttribute("currentUser",hmAccounts);
        return res;
    }
    @RequestMapping(value = "/mail/index", method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest httpServletRequest) throws Exception {
        ModelAndView modelAndView = new ModelAndView("index");
        HmAccounts hmAccounts = (HmAccounts)httpServletRequest.getSession().getAttribute("currentUser");
        List<SimpleMail> simpleMails = pop3ReceiveMail.getSimpleMail(hmAccounts.getAccountaddress(), hmAccounts.getAccountpassword());
        modelAndView.addObject(simpleMails);
        return modelAndView;
    }

    /**
     * @deprecated look through the mail details
     * @param httpServletRequest the request
     * @param id the id of mail
     * @return ModelAndView
     * @throws Exception
     */
    @RequestMapping(value = "/mail/detail", method = RequestMethod.GET)
    public ModelAndView detail(HttpServletRequest httpServletRequest, @Param("id")int id) throws Exception {
        ModelAndView modelAndView = new ModelAndView("mail_detail");
        HmAccounts hmAccounts = (HmAccounts)httpServletRequest.getSession().getAttribute("currentUser");
        Mail mail = pop3ReceiveMail.getMail(hmAccounts.getAccountaddress(), hmAccounts.getAccountpassword(),id);
        modelAndView.addObject(mail);
        return modelAndView;
    }
    @RequestMapping(value = "/mail/send/do",method = RequestMethod.POST)
    @ResponseBody
    public String sendo(HttpServletRequest httpServletRequest) throws SendMailException {
        HmAccounts hmAccounts = (HmAccounts)httpServletRequest.getSession().getAttribute("currentUser");
        OhMyEmail.config(SMTP_ZQ(false), hmAccounts.getAccountaddress(), hmAccounts.getAccountpassword());
        OhMyEmail.subject(httpServletRequest.getParameter("subject"))
                .from("tamperMonkey的邮箱")
                .to(httpServletRequest.getParameter("mailTo"))
                .html(httpServletRequest.getParameter("content"))
                .send();
        return "1";
    }
    @RequestMapping(value = "/mail/send",method = RequestMethod.GET)
    public String send(HttpServletRequest httpServletRequest){
        return "mail_send";
    }
    @RequestMapping(value = "/mail/delete", method = RequestMethod.GET)
    public String delete(HttpServletRequest httpServletRequest, @Param("id")int id) throws Exception {
        HmAccounts hmAccounts = (HmAccounts)httpServletRequest.getSession().getAttribute("currentUser");
        pop3ReceiveMail.deleteMail(hmAccounts.getAccountaddress(), hmAccounts.getAccountpassword(),id);
        return "redirect:/mail/index";
    }
}
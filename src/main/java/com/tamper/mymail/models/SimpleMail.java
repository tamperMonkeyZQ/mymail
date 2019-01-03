package com.tamper.mymail.models;

import javax.mail.Address;
import java.util.Date;

/**
 * 包含邮件的简要信息
 */
public class SimpleMail {
    int id;
    String subject;
    String from;
    String recieveTime;

    public SimpleMail() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getRecieve() {
        return recieveTime;
    }

    public void setRecieve(String recieve) {
        this.recieveTime = recieve;
    }
}

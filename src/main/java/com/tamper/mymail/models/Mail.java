package com.tamper.mymail.models;

public class Mail {
    int id;
    String subject;
    String from;
    String recieve;
    String sendTime;
    String isseen;
    String getPriority;
    String isReplySign;
    StringBuffer content;

    public Mail(int id, String subject, String from, String recieve, String sendTime, String isseen, String getPriority, String isReplySign, StringBuffer content) {
        this.id = id;
        this.subject = subject;
        this.from = from;
        this.recieve = recieve;
        this.sendTime = sendTime;
        this.isseen = isseen;
        this.getPriority = getPriority;
        this.isReplySign = isReplySign;
        this.content = content;
    }

    public Mail() {
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
        return recieve;
    }

    public void setRecieve(String recieve) {
        this.recieve = recieve;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getIsseen() {
        return isseen;
    }

    public void setIsseen(String isseen) {
        this.isseen = isseen;
    }

    public String getGetPriority() {
        return getPriority;
    }

    public void setGetPriority(String getPriority) {
        this.getPriority = getPriority;
    }

    public String getIsReplySign() {
        return isReplySign;
    }

    public void setIsReplySign(String isReplySign) {
        this.isReplySign = isReplySign;
    }

    public StringBuffer getContent() {
        return content;
    }

    public void setContent(StringBuffer content) {
        this.content = content;
    }
}

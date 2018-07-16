package com.mirrordust.telcodata.service;

public interface MailService {

    public void sendSimpleMail(String[] tos, String[] ccs, String subject, String content);

    public void sendHtmlMail(String to, String subject, String content);
}

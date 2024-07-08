package com.back.assessment.service;

public interface MailService {
    void sendMail(String from, String to, String subject);
    void mailSendForRegister(String to);
    void mailSendForForgetPassword(String to);
}

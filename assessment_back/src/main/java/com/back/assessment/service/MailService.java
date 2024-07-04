package com.back.assessment.service;

public interface MailService {
    void sendMail(String from, String to, String subject);
    void mailSend(String to);
}

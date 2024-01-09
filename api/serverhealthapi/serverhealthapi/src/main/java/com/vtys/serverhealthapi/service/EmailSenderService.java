package com.vtys.serverhealthapi.service;

public interface EmailSenderService {

    public void sendEmail(String to, String subject, String text);

    public void sendVerificationEmail(String to, String verificationCode);

}
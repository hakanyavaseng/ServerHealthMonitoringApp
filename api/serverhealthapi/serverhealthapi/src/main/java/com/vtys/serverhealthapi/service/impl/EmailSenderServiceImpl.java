package com.vtys.serverhealthapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.vtys.serverhealthapi.service.EmailSenderService;


@Service
public class EmailSenderServiceImpl implements EmailSenderService{

     private JavaMailSender javaMailSender;

    @Autowired
    public EmailSenderServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(String to, String body, String topic) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("hakanyavaseng@gmail.com");
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(topic);
        simpleMailMessage.setText(body);

        javaMailSender.send(simpleMailMessage);
    }

    public void sendVerificationEmail(String to, String verificationCode) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("hakanyavaseng@gmail.com");
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject("Doğrulama Kodunuz");
        simpleMailMessage.setText("Doğrulama kodunuz: " + verificationCode);
        
        javaMailSender.send(simpleMailMessage);

    }
    
}


  
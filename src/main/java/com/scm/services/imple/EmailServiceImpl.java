package com.scm.services.imple;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.scm.services.EmailService;

import config.Autowired;

public class EmailServiceImpl implements EmailService {

        

    @Override
    public void sendEmail(String to, String subject, String body){

        @Autowired
        private JavaMailSender eMailSender;
        
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        message.setFrom("scm@demomailtrap.com");
        eMailSender.send(message);
    }
    
}

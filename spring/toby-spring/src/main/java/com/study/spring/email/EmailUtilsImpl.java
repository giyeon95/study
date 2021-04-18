package com.study.spring.email;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtilsImpl implements EmailUtils {

    private final JavaMailSender mailSender;

    @Autowired
    public EmailUtilsImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }


    @Override
    public void send(EmailDTO emailDTO) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setTo(emailDTO.getReceiver());
            helper.setSubject(emailDTO.getSubject());
            helper.setText(emailDTO.getContents());
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        mailSender.send(message);
    }
}

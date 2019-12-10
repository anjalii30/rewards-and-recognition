package com.rar.utils;

import freemarker.template.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service
public class SendEmail  {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Configuration freemarkerConfig;


    public ResponseEntity sendEmailWithoutAttachment(String emails,String subject,String message) throws MessagingException, IOException {
        MimeMessage msg = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(msg, true);
            helper.setTo(emails);
            helper.setSubject(subject);
            helper.setText(message);
            javaMailSender.send(msg);
            return ResponseEntity.ok("Successful");

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    public ResponseEntity sendEmailWithAttachment(Map root, String emails, String subject) throws MessagingException, IOException, TemplateException {

        MimeMessage msg = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(msg, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

            Template t = freemarkerConfig.getTemplate("abc.html");
            String text = FreeMarkerTemplateUtils.processTemplateIntoString(t, root);

            helper.setTo(emails);
            helper.setSubject(subject);
            helper.setText(text, true);


            javaMailSender.send(msg);
            return ResponseEntity.ok("Successful");

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}

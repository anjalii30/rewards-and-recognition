package com.rar.service.impl;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
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

import static com.rar.utils.Constants.SUCCESS;

@Service
public class SendEmail  {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Configuration freemarkerConfig;

//used when award is rolled out or discontinued
    public ResponseEntity sendEmailWithoutAttachment(String emails,String subject,String message) throws MessagingException, IOException {
        MimeMessage msg = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(msg, true);
            helper.setTo(emails);
            helper.setSubject(subject);
            helper.setText(message);
            javaMailSender.send(msg);
            return ResponseEntity.ok(SUCCESS);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }


//used only for winner email
    public ResponseEntity sendEmailWithAttachment(Map root, String emails, String subject) throws MessagingException, IOException, TemplateException {

        MimeMessage msg = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(msg, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());


            Template t = freemarkerConfig.getTemplate("Winner.html");
            String text = FreeMarkerTemplateUtils.processTemplateIntoString(t, root);

            helper.setTo(emails);
            helper.setSubject(subject);
            helper.setText(text, true);


            javaMailSender.send(msg);
            return ResponseEntity.ok(SUCCESS);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    //used for self winner email
    public ResponseEntity sendEmailToWinner(Map root, String emails, String subject) throws MessagingException, IOException, TemplateException {

        MimeMessage msg = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(msg, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());


            Template t = freemarkerConfig.getTemplate("selfWinner.html");
            String text = FreeMarkerTemplateUtils.processTemplateIntoString(t, root);

            helper.setTo(emails);
            helper.setSubject(subject);
            helper.setText(text, true);


            javaMailSender.send(msg);
            return ResponseEntity.ok(SUCCESS);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}

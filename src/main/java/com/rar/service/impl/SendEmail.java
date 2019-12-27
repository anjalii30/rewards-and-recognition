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
    public ResponseEntity sendEmailWithoutAttachment(String emails,String subject,String message) throws MessagingException {
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
    public void sendEmailWithAttachment(Map root, String emails, String subject) throws MessagingException, TemplateException {

        String template="Winner.html";
        sendEmail(root,emails,subject,template);

    }

    //used for self winner email
    public void sendEmailToWinner(Map root, String emails, String subject) throws MessagingException, TemplateException {

        String template="selfWinner.html";
        sendEmail(root,emails,subject,template);

    }
    public ResponseEntity sendEmail(Map root,String emails,String subject,String template)throws MessagingException,TemplateException{
        MimeMessage msg = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(msg, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());


            Template t = freemarkerConfig.getTemplate(template);
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

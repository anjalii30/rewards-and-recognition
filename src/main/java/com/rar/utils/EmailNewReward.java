package com.rar.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;

@Service
public class EmailNewReward {

    @Autowired
    private JavaMailSender javaMailSender;

   // private static final String PATH="/home/nineleaps/Desktop/github/rar/src/main/resources/image.jpg";


    public void sendEmail() throws IOException, MessagingException {

//        SimpleMailMessage msg= new SimpleMailMessage();
//        msg.setTo("anjali.garg@nineleaps.com","reshma.kosana@nineleaps.com");
//        msg.setSubject("Testing from SpringBoot");
//        msg.setText("Hello World \n Spring Boot Email");
//
//        javaMailSender.send(msg);
        sendEmailWithoutAttachment("anjali.garg@nineleaps.com","Testing from SpringBoot","Hello World \\n Spring Boot Email ");

    }
    public void sendEmailWithoutAttachment(String emails,String subject,String message) throws MessagingException, IOException {
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setTo(emails);
        helper.setSubject(subject);
        helper.setText(message);
        helper.setText(message, true);
        //  helper.addAttachment("my_photo.png", new ClassPathResource("victory.png"));
        javaMailSender.send(msg);
    }

    public void sendEmailWithAttachment(String emails,String subject) throws MessagingException, IOException {

        MimeMessage msg = javaMailSender.createMimeMessage();

        // true = multipart message
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);

        helper.setTo(emails);

        helper.setSubject(subject);

        // default = text/plain
        //helper.setText("Check attachment for image!");

        // true = text/html
        helper.setText("<h1>Check attachment for image! (sent by file system resource and file.getname and file as fileresource)</h1>", true);

        // hard coded a file path
        FileSystemResource file = new FileSystemResource(new File("src/main/resources/image.jpg"));

        //helper.addAttachment("image.jpg", new ClassPathResource("image.jpg"));
        helper.addAttachment(file.getFilename(),file);

        javaMailSender.send(msg);

    }
}

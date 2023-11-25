package com.skawuma.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Date;

@Service
public class NotificationService {
    @Autowired
    private ReportService service;
    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String sender;
    @Value("${report.send.email.toList}")
    private String toEmails;
    public String sendDailyReports() throws MessagingException, IOException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);

        mimeMessageHelper.setFrom(sender);
        mimeMessageHelper.setTo(toEmails.split(","));
        mimeMessageHelper.setSubject("List of Orders_"+ new Date().getTime());
        mimeMessageHelper.setText("Hi User\n Please Kindly do find the attachment for today's order received");
        try {
            byte[] report = service.generateReport();
            ByteArrayResource content = new ByteArrayResource(report);
            mimeMessageHelper.addAttachment(new Date().getTime()+"_orders.xlsx",content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//        FileSystemResource file = new FileSystemResource(new File(request.getAttachment()));
       ;

        javaMailSender.send(mimeMessage);
        return "Mail sent successfully with Attachment";


    }

}

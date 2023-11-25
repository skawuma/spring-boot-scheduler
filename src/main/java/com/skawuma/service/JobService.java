package com.skawuma.service;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;

@Service
public class JobService {
    @Autowired
    private NotificationService service;
    @Scheduled(cron = "${cron_interval}", zone= "EST")
    public void process() throws MessagingException, IOException {
        System.out.println("Job Started on" + new Date());
        try {
            service.sendDailyReports();
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


}

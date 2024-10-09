package com.example.pleno_spring.controller;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class ScheduledTaskController {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Scheduled(fixedRate = 5000)
    public void scheduledTask() {
        System.out.println("Scheduled task executed at: " + dateTimeFormatter.format(LocalDateTime.now()));
    }
}
package com.example.finshot.global.config;

import com.example.finshot.application.EmployeeListSendEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class ScheduleConfig {

    // 0 0/1 * * * * -> 1분마다
    // 0 0 12 * * * -> 12시마
    // 0/10 * * * * ? -> 10초마다
    private static final String SENDING_TIME = "0/10 * * * * ?";
    private final EmployeeListSendEmailService emailService;

    @Scheduled(cron = SENDING_TIME)
    public void sendEmailEmployeeList() {
        emailService.sendEmployeeListEmail();
    }

}

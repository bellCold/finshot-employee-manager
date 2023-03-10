package com.example.finshot.application;

import com.example.finshot.application.mail.EmailMessage;
import com.example.finshot.application.mail.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeListSendEmailService {

    @Value("${receiver.email.address}")
    private String receiverEmail;

    private final EmailService emailService;
    private final String EMAIL_SUBJECT = LocalDateTime.now().format(ISO_LOCAL_DATE) + "finShot 직원리스트";

    public void sendEmployeeListEmail() {
        try {
            EmailMessage emailMessage = EmailMessage.builder()
                    .to(receiverEmail)
                    .subject(EMAIL_SUBJECT)
                    .message("직원리스트")
                    .build();

            emailService.sendEmail(emailMessage);
        } catch (Exception e) {
            log.warn("전송 실패: {}", e);
        }
    }
}

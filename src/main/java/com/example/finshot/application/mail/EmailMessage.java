package com.example.finshot.application.mail;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class EmailMessage {
    private String to;
    private String subject;
    private String message;


    @Builder
    public EmailMessage(String to, String subject, String message) {
        this.to = to;
        this.subject = subject;
        this.message = message;
    }
}

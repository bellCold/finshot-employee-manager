package com.example.finshot.application.mail;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.File;

@Getter
@Setter
@NoArgsConstructor
public class EmailMessage {
    private String to;
    private String subject;
    private String message;
    private String fileName;
    private File file;

    @Builder
    public EmailMessage(String to, String subject, String message, String fileName, File file) {
        this.to = to;
        this.subject = subject;
        this.message = message;
        this.fileName = fileName;
        this.file = file;
    }
}

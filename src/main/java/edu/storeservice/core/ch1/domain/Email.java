package edu.storeservice.core.ch1.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Email {

    private Long mailNo;
    private String fromEmail;
    private String toEmail;
    private String content;
    private String smtpServer;

    @Override
    public String toString() {
        return "Email{" + "\n" +
                "fromEmail='" + fromEmail + '\'' + "\n" +
                ", toEmail='" + toEmail + '\'' + "\n" +
                ", content='" + content + '\'' + "\n" +
                ", smtpServer='" + smtpServer + '\'' + "\n" +
                '}';
    }
}

package edu.storeservice.core.ch2.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Email {

    private Long id;
    private String fromEmail;
    private String toEmail;
    private String content;

    public Email(String fromEmail, String toEmail, String content){
        this.fromEmail = fromEmail;
        this.toEmail = toEmail;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Email{" + "\n" +
                "id='" + id + '\'' + "\n" +
                "fromEmail='" + fromEmail + '\'' + "\n" +
                ", toEmail='" + toEmail + '\'' + "\n" +
                ", content='" + content + '\'' + "\n" +
                '}';
    }
}
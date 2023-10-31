package edu.storeservice.core.ch1.iocexample.service;

import edu.storeservice.core.ch1.iocexample.repository.EmailMSRepository;
import edu.storeservice.core.ch1.iocexample.repository.EmailRepository;
import edu.storeservice.core.ch1.domain.Email;

public class GmailSMTPService implements EmailService{

    //EmailMSRepository 를 직접 생성해서 사용
    //EmailMSRepository emailRepository = new EmailMSRepository();

    //생성자 주입을 통해 의존관계 설정
    public final EmailRepository emailRepository;

    public GmailSMTPService(EmailRepository emailRepository){
        this.emailRepository = emailRepository;
    }

    private static String smtpType = "GMAIL";

    public Email send(String fromEmail, String toEmail, String content){

        Email email = new Email(1L, fromEmail, toEmail, content, smtpType);
        emailRepository.save(email);

        return email;
    }

}
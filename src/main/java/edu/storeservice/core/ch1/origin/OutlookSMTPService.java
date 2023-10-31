package edu.storeservice.core.ch1.origin;

import edu.storeservice.core.ch1.domain.Email;

public class OutlookSMTPService {

    // EmailMSRepository 를 직접 생성해서 사용
    EmailMSRepository emailRepository = new EmailMSRepository();
    private static String smtpType = "OUTLOOK";

    public Email send(String fromEmail, String toEmail, String content){

        Email transferMail = new Email(1L, fromEmail, toEmail, content, smtpType);
        emailRepository.save(transferMail);

        return transferMail;
    }

}

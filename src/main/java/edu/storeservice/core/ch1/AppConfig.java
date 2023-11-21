package edu.storeservice.core.ch1;

import edu.storeservice.core.ch1.iocexample.repository.EmailMSRepository;
import edu.storeservice.core.ch1.iocexample.repository.EmailRepository;
import edu.storeservice.core.ch1.iocexample.service.EmailService;
import edu.storeservice.core.ch1.iocexample.service.GmailSMTPService;
import edu.storeservice.core.ch1.iocexample.service.OutlookSMTPService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public EmailRepository emailRepository(){
        return new EmailMSRepository();
    }

    @Bean
    public EmailService emailService() {
        return new GmailSMTPService(emailRepository());
    }
}

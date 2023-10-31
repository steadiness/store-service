package edu.storeservice.core.ch1.iocexample;

import edu.storeservice.core.ch1.AppConfig;
import edu.storeservice.core.ch1.iocexample.service.EmailService;
import edu.storeservice.core.ch1.domain.Email;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class IocCode {

    public static void main(String[] args){

        //OutlookSMTPService 를 직접 생성해서 사용
        //OutlookSMTPService emailService = new OutlookSMTPService();;

        //IoC Container
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        EmailService emailService = applicationContext.getBean("emailService", EmailService.class);

        Email transEmail =  emailService.send("aaa@lotte.net", "bbb@naver.com", "메일 내용 입니다.");

        log.info("mail = \n{}", transEmail);

    }

}

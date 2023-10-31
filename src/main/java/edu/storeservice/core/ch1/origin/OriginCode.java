package edu.storeservice.core.ch1.origin;

import edu.storeservice.core.ch1.domain.Email;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OriginCode {

    public static void main(String[] args){

        // OutlookSMTPService 를 직접 생성해서 사용
        OutlookSMTPService emailService = new OutlookSMTPService();

        Email transEmail =  emailService.send("aaa@lotte.net", "bbb@naver.com", "메일 내용 입니다.");

        log.info("mail = \n{}", transEmail);

    }
}

package edu.storeservice.core.ch1.diexample;

import edu.storeservice.core.ch1.domain.Email;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

//@RequiredArgsConstructor
@Slf4j
@Controller
public class SampleController {

    // 필드 주입
    @Autowired
    SampleService sampleService;

    // 수정자 주입
    //private SampleService sampleService;
    //@Autowired public void setSampleService(SampleService sampleService) { this.sampleService = sampleService; }

    // 메소드 주입
    //private SampleService sampleService;
    //@Autowired public void init(SampleService sampleService) { this.sampleService = sampleService; }

    // 생성자 주입
    //private final SampleService sampleService;
    //@Autowired public SampleController(SampleService sampleService){ this.sampleService = sampleService; }

    public void send(){
        Email transEmail =  sampleService.send("aaa@lotte.net", "bbb@naver.com", "메일 내용 입니다.");

        log.info("mail = \n{}", transEmail);
    }
}

package edu.storeservice;

import edu.storeservice.core.ch2.domain.Email;
import edu.storeservice.core.ch2.domain.EmailWebRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInit {

    private final EmailWebRepository emailWebRepository;

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        emailWebRepository.save(new Email("AAA@gamil.com", "BBB@naver.com", "test content 1"));
        emailWebRepository.save(new Email("CCC@lotte.net", "DDD@gmail.com", "test content 2"));

    }

}

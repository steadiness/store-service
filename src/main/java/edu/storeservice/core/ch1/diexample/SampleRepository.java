package edu.storeservice.core.ch1.diexample;

import edu.storeservice.core.ch1.domain.Email;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Repository
public class SampleRepository {

    private Map<Long, Email> emailStore = new HashMap<>();

    public void save(Email email) {
        emailStore.put(email.getMailNo(), email);
        log.info("### mail 저장 성공 ###");
    }

    public Email findById(Long mailNo) {
        return emailStore.get(mailNo);
    }
}

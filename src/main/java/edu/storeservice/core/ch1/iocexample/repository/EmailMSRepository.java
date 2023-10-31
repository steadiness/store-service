package edu.storeservice.core.ch1.iocexample.repository;

import edu.storeservice.core.ch1.domain.Email;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class EmailMSRepository implements EmailRepository{

    private Map<Long, Email> emailStore = new HashMap<>();

    @Override
    public void save(Email email) {
        emailStore.put(email.getMailNo(), email);
        log.info("### mail 저장 성공 ###");
    }

    @Override
    public Email findById(Long mailNo) {
        return emailStore.get(mailNo);
    }
}

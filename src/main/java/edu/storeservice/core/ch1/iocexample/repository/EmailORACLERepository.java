package edu.storeservice.core.ch1.iocexample.repository;

import edu.storeservice.core.ch1.domain.Email;

import java.util.HashMap;
import java.util.Map;

public class EmailORACLERepository implements EmailRepository{

    // emailRepository 를 직접 생성해서 사용
    private static Map<Long, Email> emailStore = new HashMap<>();

    @Override
    public void save(Email email) {
        emailStore.put(email.getMailNo(), email);
    }

    @Override
    public Email findById(Long mailNo) {
        return emailStore.get(mailNo);
    }
}

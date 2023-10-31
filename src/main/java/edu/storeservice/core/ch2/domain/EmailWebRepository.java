package edu.storeservice.core.ch2.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class EmailWebRepository {

    private final static Map<Long, Email> store = new HashMap<>();
    private static long sequence = 0L;

    public Email save(Email email){
        email.setId(++sequence);
        store.put(email.getId(), email);
        return email;
    }

    public Email findById(Long id){
        return store.get(id);
    }

    public List<Email> findAll(){
        for (Email value : store.values()) {
            log.info(value.toString());
        }
        return new ArrayList<>(store.values());
    }

    public void clear(){
        store.clear();
    }

}

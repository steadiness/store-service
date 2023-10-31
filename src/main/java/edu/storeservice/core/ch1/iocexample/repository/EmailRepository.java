package edu.storeservice.core.ch1.iocexample.repository;

import edu.storeservice.core.ch1.domain.Email;

public interface EmailRepository {

    void save(Email email);

    Email findById(Long mailNo);

}

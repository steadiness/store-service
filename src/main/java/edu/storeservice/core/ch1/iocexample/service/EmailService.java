package edu.storeservice.core.ch1.iocexample.service;

import edu.storeservice.core.ch1.domain.Email;

public interface EmailService {

    Email send(String fromEmail, String toEmail, String content);
}

package edu.storeservice.core.ch1.diexample;

import edu.storeservice.core.ch1.domain.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SampleService {

    private final SampleRepository sampleRepository;

    private static String smtpType = "SAMPLE";

    public Email send(String fromEmail, String toEmail, String content){

        Email email = new Email(1L, fromEmail, toEmail, content, smtpType);
        sampleRepository.save(email);

        return email;
    }
}

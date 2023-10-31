package edu.storeservice.core.ch2.web.email;

import edu.storeservice.core.ch2.domain.Email;
import edu.storeservice.core.ch2.domain.EmailWebRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailWebService {

    private final EmailWebRepository emailWebRepository;

    public List<Email> findAll(){
        return emailWebRepository.findAll();
    }
}

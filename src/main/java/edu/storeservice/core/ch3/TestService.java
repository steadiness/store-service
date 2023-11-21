package edu.storeservice.core.ch3;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;

@Slf4j
@RequiredArgsConstructor
public class TestService {

    private final TestRepository testRepository;

    public void transfer(String fromName, String toName, int money) throws SQLException {

        Account fromAccount = testRepository.findByName(fromName);
        Account toAccount = testRepository.findByName(toName);

        testRepository.update(fromAccount, fromAccount.getBalance() - money);
        if(toAccount.getName().equals("EXCEPTION"))
            throw new IllegalStateException("transfer fail");
        testRepository.update(toAccount, toAccount.getBalance() + money);

    }
}

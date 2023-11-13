package edu.storeservice.core.ch3;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Slf4j
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    @Transactional
    public void transfer(String fromName, String toName, int money) throws SQLException {

        Account fromAccount = accountRepository.findByName(fromName);
        Account toAccount = accountRepository.findByName(toName);

        accountRepository.update(fromAccount, fromAccount.getBalance() - money);
        if (toAccount.getName().equals("EXCEPTION"))
            throw new IllegalStateException("transfer fail");
        accountRepository.update(toAccount, toAccount.getBalance() + money);

    }
}

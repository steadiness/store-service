package edu.storeservice.core.ch3;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
@RequiredArgsConstructor
public class AccountJdbcService {

    private final AccountJdbcRepository accountJdbcRepository;

    public void transfer(String fromName, String toName, int money) throws SQLException {

        Account fromAccount = accountJdbcRepository.findByName(fromName);
        Account toAccount = accountJdbcRepository.findByName(toName);

        accountJdbcRepository.update(fromAccount, fromAccount.getBalance() - money);
        if(toAccount.getName().equals("EXCEPTION"))
            throw new IllegalStateException("transfer fail");
        accountJdbcRepository.update(toAccount, toAccount.getBalance() + money);

    }

}

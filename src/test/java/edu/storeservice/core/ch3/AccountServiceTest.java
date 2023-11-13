package edu.storeservice.core.ch3;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

import static edu.storeservice.core.ch3.ConnectionConst.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
@SpringBootTest
class AccountServiceTest {

    public static final String NAME_BALLY = "BELLY";
    public static final String NAME_LOTTY = "LOTTY";
    public static final String NAME_EXCEPTION = "EXCEPTION";

    @Autowired
    AccountService accountService;
    @Autowired
    AccountRepository accountRepository;

    @TestConfiguration
    static class TestConfig {
        @Bean
        DataSource dataSource() {
            return new DriverManagerDataSource(URL, USERNAME, PASSWORD);
        }
        @Bean
        PlatformTransactionManager transactionManager() {
            return new DataSourceTransactionManager(dataSource());
        }

        @Bean
        AccountRepository accountRepository() {
            return new AccountRepository(dataSource());
        }
        @Bean
        AccountService accountService() {
            return new AccountService(accountRepository());
        }
    }

    @AfterEach
    void after() throws SQLException {
        accountRepository.delete();
    }


    @Test
    @DisplayName("정상 이체")
    void accountTransfer() throws SQLException {

        Account fromAccount = new Account(NAME_BALLY, 10000);
        Account toAccount = new Account(NAME_LOTTY, 10000);
        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        accountService.transfer(fromAccount.getName(), toAccount.getName(), 2000);

        Account findAccount1 = accountRepository.findByName(fromAccount.getName());
        Account findAccount2 = accountRepository.findByName(toAccount.getName());

        assertThat(findAccount1.getBalance()).isEqualTo(8000);
        assertThat(findAccount2.getBalance()).isEqualTo(12000);
    }

    @Test
    @DisplayName("이체중 예외 발생")
    void accountTransferEx() throws SQLException {

        Account fromAccount = new Account(NAME_BALLY, 10000);
        Account toAccount = new Account(NAME_EXCEPTION, 10000);
        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        assertThatThrownBy(() ->
                accountService.transfer(fromAccount.getName(), toAccount.getName(), 2000))
                .isInstanceOf(IllegalStateException.class);

        Account findAccount1 = accountRepository.findByName(fromAccount.getName());
        Account findAccount2 = accountRepository.findByName(toAccount.getName());

        assertThat(findAccount1.getBalance()).isEqualTo(10000);
        assertThat(findAccount2.getBalance()).isEqualTo(10000);
    }

}
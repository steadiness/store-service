package edu.storeservice.core.ch3;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;

import java.sql.SQLException;

import static edu.storeservice.core.ch3.ConnectionConst.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AccountProgrammingServiceTest {

    public static final String NAME_BALLY = "BELLY";
    public static final String NAME_LOTTY = "LOTTY";
    public static final String NAME_EXCEPTION = "EXCEPTION";

    private AccountProgrammingService accountProgrammingService;
    private AccountProgrammingRepository accountProgrammingRepository;

    @BeforeEach
    void before() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);
        PlatformTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
        accountProgrammingRepository = new AccountProgrammingRepository(dataSource);
        accountProgrammingService = new AccountProgrammingService(transactionManager, accountProgrammingRepository);
    }

    @AfterEach
    void after() throws SQLException {
        accountProgrammingRepository.delete();
    }

    @Test
    @DisplayName("정상 이체")
    void accountTransfer() throws SQLException {

        Account fromAccount = new Account(NAME_BALLY, 10000);
        Account toAccount = new Account(NAME_LOTTY, 10000);
        accountProgrammingRepository.save(fromAccount);
        accountProgrammingRepository.save(toAccount);

        accountProgrammingService.transfer(fromAccount.getName(), toAccount.getName(), 2000);

        Account findAccount1 = accountProgrammingRepository.findByName(fromAccount.getName());
        Account findAccount2 = accountProgrammingRepository.findByName(toAccount.getName());

        assertThat(findAccount1.getBalance()).isEqualTo(8000);
        assertThat(findAccount2.getBalance()).isEqualTo(12000);
    }

    @Test
    @DisplayName("이체중 예외 발생")
    void accountTransferEx() throws SQLException {

        Account fromAccount = new Account(NAME_BALLY, 10000);
        Account toAccount = new Account(NAME_EXCEPTION, 10000);
        accountProgrammingRepository.save(fromAccount);
        accountProgrammingRepository.save(toAccount);

        assertThatThrownBy(() ->
                accountProgrammingService.transfer(fromAccount.getName(), toAccount.getName(), 2000))
                .isInstanceOf(IllegalStateException.class);

        Account findAccount1 = accountProgrammingRepository.findByName(fromAccount.getName());
        Account findAccount2 = accountProgrammingRepository.findByName(toAccount.getName());

        assertThat(findAccount1.getBalance()).isEqualTo(10000);
        assertThat(findAccount2.getBalance()).isEqualTo(10000);
    }

}
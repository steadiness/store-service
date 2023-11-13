package edu.storeservice.core.ch3;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AccountJdbcServiceTest {

    public static final String NAME_BALLY = "BELLY";
    public static final String NAME_LOTTY = "LOTTY";
    public static final String NAME_EXCEPTION = "EXCEPTION";

    private AccountJdbcService accountJdbcService;
    private AccountJdbcRepository accountJdbcRepository;

    @BeforeEach
    void before() {
        accountJdbcRepository = new AccountJdbcRepository();
        accountJdbcService = new AccountJdbcService(accountJdbcRepository);
    }

    @AfterEach
    void after() throws SQLException {
        accountJdbcRepository.delete();
    }

    @Test
    @DisplayName("정상 이체")
    void accountTransfer() throws SQLException {

        Account fromAccount = new Account(NAME_BALLY, 10000);
        Account toAccount = new Account(NAME_LOTTY, 10000);
        accountJdbcRepository.save(fromAccount);
        accountJdbcRepository.save(toAccount);

        accountJdbcService.transfer(fromAccount.getName(), toAccount.getName(), 2000);

        Account findAccount1 = accountJdbcRepository.findByName(fromAccount.getName());
        Account findAccount2 = accountJdbcRepository.findByName(toAccount.getName());

        assertThat(findAccount1.getBalance()).isEqualTo(8000);
        assertThat(findAccount2.getBalance()).isEqualTo(12000);
    }

    @Test
    @DisplayName("이체중 예외 발생")
    void accountTransferEx() throws SQLException {

        Account fromAccount = new Account(NAME_BALLY, 10000);
        Account toAccount = new Account(NAME_EXCEPTION, 10000);
        accountJdbcRepository.save(fromAccount);
        accountJdbcRepository.save(toAccount);

        assertThatThrownBy(() ->
                accountJdbcService.transfer(fromAccount.getName(), toAccount.getName(), 2000))
                .isInstanceOf(IllegalStateException.class);

        Account findAccount1 = accountJdbcRepository.findByName(fromAccount.getName());
        Account findAccount2 = accountJdbcRepository.findByName(toAccount.getName());

        assertThat(findAccount1.getBalance()).isEqualTo(8000);
        assertThat(findAccount2.getBalance()).isEqualTo(10000);
    }


}
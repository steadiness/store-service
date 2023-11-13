package edu.storeservice.core.ch3;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.sql.SQLException;

@Slf4j
@RequiredArgsConstructor
public class AccountProgrammingService {

    private final PlatformTransactionManager transactionManager;
    private final AccountProgrammingRepository accountProgrammingRepository;

    public void transfer(String fromName, String toName, int money){

        //트랜잭션 시작
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            Account fromAccount = accountProgrammingRepository.findByName(fromName);
            Account toAccount = accountProgrammingRepository.findByName(toName);

            accountProgrammingRepository.update(fromAccount, fromAccount.getBalance() - money);
            if (toAccount.getName().equals("EXCEPTION"))
                throw new IllegalStateException("transfer fail");
            accountProgrammingRepository.update(toAccount, toAccount.getBalance() + money);

            //트랜잭션 커밋
            transactionManager.commit(status);

        } catch (Exception e) {
            //트랜잭션 롤백
            transactionManager.rollback(status);
            throw new IllegalStateException(e);
        }


    }


}

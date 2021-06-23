package com.pichincha.backend.test.unit;

import com.pichincha.backend.test.dto.AccountDto;
import com.pichincha.backend.test.dto.AccountResponseDto;
import com.pichincha.backend.test.dto.NewTransactionDto;
import com.pichincha.backend.test.dto.rest.RestTransaction;
import com.pichincha.backend.test.iservices.IAccountService;
import com.pichincha.backend.test.iservices.ITransactionService;
import com.pichincha.backend.test.model.Account;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@SpringBootTest
@Slf4j
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AccountServicesTest {

    @Autowired
    private IAccountService accountService;

    @Autowired
    private ITransactionService transactionService;

    private AccountDto account;

    @BeforeAll
    public void getAccountId(){
        account = accountService.getAccount("1234567890");
    }

    @Test
    public void testCreateTransaction() {
        log.info("account id:{}",account.getId());

        NewTransactionDto newTransactionDto = NewTransactionDto.builder().accountId(account.getId().toString()).amount(40.0)
                .comment("Transaccion de prueba 3").type("1").build();

        UUID id = transactionService.addTransaction(newTransactionDto);

        Assertions.assertNotNull(id);
    }

    @Test
    public void testListTransactions(){
        Random random = new Random();

        for(int i= 0; i < 5; i++){
            NewTransactionDto newTransactionDto = NewTransactionDto.builder().accountId(account.getId().toString()).amount(random.nextInt(100))
                    .comment("Transaccion de prueba "+i).type("1").build();

            transactionService.addTransaction(newTransactionDto);
        }

        AccountResponseDto accountResponseDto = transactionService.getTransactionsForAccount(account.getId());
        accountResponseDto.getTransactions().forEach(transaction -> {
            log.info("type: {}, mount: {}", transaction.getType(), transaction.getAmount());
        });
        Assertions.assertFalse(accountResponseDto.getTransactions().isEmpty());
    }


}

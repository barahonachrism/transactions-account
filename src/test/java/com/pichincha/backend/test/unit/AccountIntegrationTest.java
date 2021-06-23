package com.pichincha.backend.test.unit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pichincha.backend.test.dto.AccountDto;
import com.pichincha.backend.test.dto.NewTransactionDto;
import com.pichincha.backend.test.dto.rest.TransactionContainer;
import com.pichincha.backend.test.iservices.IAccountService;
import com.pichincha.backend.test.iservices.ITransactionService;
import com.pichincha.backend.test.model.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PostConstruct;
import javax.validation.constraints.AssertTrue;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Slf4j
public class AccountIntegrationTest {
    @Autowired
    private IAccountService accountService;

    @Autowired
    private ITransactionService transactionService;

    private AccountDto account;

    @Autowired
    private ObjectMapper jsonMapper;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


    @BeforeAll
    @PostConstruct
    public void getAccountId(){

        account = accountService.getAccount("1234567890");
        Random random = new Random();

        for(int i= 0; i < 5; i++){
            NewTransactionDto newTransactionDto = NewTransactionDto.builder().accountId(account.getId().toString()).amount(random.nextInt(100))
                    .comment("Transaccion de prueba "+i).type("1").build();

            transactionService.addTransaction(newTransactionDto);
        }

    }

    @Test
    public void testFindTransactionsByAccount() throws JsonProcessingException {
        ResponseEntity<TransactionContainer> transactionContainerResponseEntity = restTemplate.getForEntity("http://localhost:"+port+"/transactions/{accountId}", TransactionContainer.class,account.getId().toString());
        Assertions.assertTrue(transactionContainerResponseEntity.getStatusCodeValue() == 200);
        log.info("Transacciones encontradas: {}",jsonMapper.writeValueAsString(transactionContainerResponseEntity.getBody()));
    }

    @Test
    public void testFindTransactionsByAccountAndAmountRange() throws JsonProcessingException {
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put("accountId", account.getId().toString());

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("http://localhost:"+port+"/transactions/{accountId}");
        builder.queryParam("minAmount",1)
                .queryParam("maxAmount",5);
        URI uri =  builder.buildAndExpand(urlParams).toUri();
        log.info("Url de consulta de transacciones por rango: ",uri.toString());
        ResponseEntity<TransactionContainer> transactionContainerResponseEntity = restTemplate.getForEntity(uri, TransactionContainer.class);
        Assertions.assertTrue(transactionContainerResponseEntity.getStatusCodeValue() == 200);
        log.info("Transacciones encontradas por montos: {}",jsonMapper.writeValueAsString(transactionContainerResponseEntity.getBody()));
    }
}

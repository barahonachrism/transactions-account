package com.pichincha.backend.test.mapper;

import com.pichincha.backend.test.dto.*;
import com.pichincha.backend.test.model.Account;
import com.pichincha.backend.test.model.Transaction;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class ServiceMapper {

  public AccountResponseDto mapResponse() {
    return null;
  }

  public AccountDto getAccountDto(Optional<Account> account){
    if(account.isPresent()){
      Account accountAux = account.get();
      return new AccountDto(accountAux.getNumber(), accountAux.getType(), accountAux.getCreationDate());

    }
    return null;
  }

  public Transaction getTransaction(NewTransactionDto transactionDto, String accountId){
    UUID id = null;
    Timestamp creationDate = new Timestamp(System.currentTimeMillis());
    UUID uuidAccountId = UUID.fromString(accountId);
    Account account = null;
    Transaction transaction = new Transaction(id, transactionDto.getAmount(),transactionDto.getType(),creationDate,uuidAccountId,transactionDto.getComment(),null);
    return transaction;
  }

  public TransactionResponseDto getTransactionDto(Transaction transaction){
    return new TransactionResponseDto(transaction.getType(),transaction.getAmount());
  }

  public List<TransactionResponseDto> getAllTransactionDto(List<Transaction> transactions){
    List<TransactionResponseDto> transactionResponseDtoList = new ArrayList<>();
    if(transactions == null || transactions.isEmpty()){
      return transactionResponseDtoList;
    }
    for (Transaction transaction:transactions) {
      transactionResponseDtoList.add(getTransactionDto(transaction));
    }
    return transactionResponseDtoList;
  }

}

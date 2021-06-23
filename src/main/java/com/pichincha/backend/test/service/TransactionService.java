package com.pichincha.backend.test.service;

import com.pichincha.backend.test.dto.AccountResponseDto;
import com.pichincha.backend.test.dto.NewTransactionDto;
import com.pichincha.backend.test.dto.TransactionDto;
import com.pichincha.backend.test.dto.TransactionResponseDto;
import com.pichincha.backend.test.dto.rest.RestTransaction;
import com.pichincha.backend.test.iservices.ITransactionService;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.pichincha.backend.test.mapper.IServiceMapper;
import com.pichincha.backend.test.model.Transaction;
import com.pichincha.backend.test.repository.ITransactionRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class TransactionService implements ITransactionService {

  @Autowired
  private ITransactionRepository transactionRepository;

  @Override
  public AccountResponseDto getTransactionsForAccount(UUID accountId) {
    List<Transaction> transactions = transactionRepository.findTransactionsByAccount(accountId);
    List<TransactionResponseDto> transactionResponseDtoList = IServiceMapper.INSTANCE.transactionListToTransactionResponseDtoList(transactions);
    return new AccountResponseDto(accountId.toString(),transactionResponseDtoList);
  }

  @Override
  public AccountResponseDto getFilteredTransactions(UUID accountId, double minimum,
      double maximum) {
    List<Transaction> transactions = transactionRepository.findTransactionsByAccountIdAndAmounts(accountId,minimum,maximum);
    List<TransactionResponseDto> transactionResponseDtoList = IServiceMapper.INSTANCE.transactionListToTransactionResponseDtoList(transactions);
    return new AccountResponseDto(accountId.toString(),transactionResponseDtoList);
  }

  public UUID addTransaction(NewTransactionDto newTransactionDto) {
    Transaction transaction = IServiceMapper.INSTANCE.newTransactionDtoToTransaction(newTransactionDto);
    transaction.setCreationDate(LocalDateTime.now());
    Transaction transactionCreated = transactionRepository.save(transaction);
    return transactionCreated.getId();
  }
}

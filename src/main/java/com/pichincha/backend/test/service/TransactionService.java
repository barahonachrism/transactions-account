package com.pichincha.backend.test.service;

import com.pichincha.backend.test.dto.AccountResponseDto;
import com.pichincha.backend.test.dto.NewTransactionDto;
import com.pichincha.backend.test.dto.TransactionDto;
import com.pichincha.backend.test.dto.TransactionResponseDto;
import com.pichincha.backend.test.iservices.ITransactionService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.pichincha.backend.test.mapper.ServiceMapper;
import com.pichincha.backend.test.model.Transaction;
import com.pichincha.backend.test.repository.ITransactionRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class TransactionService implements ITransactionService {

  @Autowired
  private ServiceMapper serviceMapper;

  @Autowired
  private ITransactionRepository transactionRepository;

  //todo: return a list of transactions from database and consume API and return both results in a single object ordered by creation date descending and separated by type
  // extra point if mapping is made with functions
  //todo: https://run.mocky.io/v3/1ac36ba6-9535-484d-93b2-fd6c68044884 API if service does return OK but has no information it just returns an empty list
  //todo: API if service does return OK but has no information it just returns an empty list
  //todo: Validate status if 0 ok if not empty list
  @Override
  public Map<String, List<TransactionDto>> getTransactionsForAccount(UUID accountId) {
    return new HashMap<>();
  }


  @Override
  public AccountResponseDto getFilteredTransactions(UUID accountId, double minimum,
      double maximum) {
    List<Transaction> transactions = transactionRepository.findTransactionsByAmounts(accountId,minimum,maximum);
    List<TransactionResponseDto> transactionResponseDtoList = serviceMapper.getAllTransactionDto(transactions);
    return new AccountResponseDto(accountId.toString(),transactionResponseDtoList);

  }

  public UUID addTransaction(NewTransactionDto newTransactionDto, String accountId) {
    Transaction transaction = serviceMapper.getTransaction(newTransactionDto,accountId);
    Transaction transactionCreated = transactionRepository.save(transaction);
    return transactionCreated.getId();
  }
}

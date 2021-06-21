package com.pichincha.backend.test.service;

import com.pichincha.backend.test.dto.AccountResponseDto;
import com.pichincha.backend.test.dto.TransactionDto;
import com.pichincha.backend.test.iservices.ITransactionService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class TransactionService implements ITransactionService {


  //todo: return a list of transactions from database and consume API and return both results in a single object ordered by creation date descending and separated by type
  // extra point if mapping is made with functions
  //todo: https://run.mocky.io/v3/1ac36ba6-9535-484d-93b2-fd6c68044884 API if service does return OK but has no information it just returns an empty list
  //todo: API if service does return OK but has no information it just returns an empty list
  //todo: Validate status if 0 ok if not empty list
  @Override
  public Map<String, List<TransactionDto>> getTransactionsForAccount(UUID accountId) {
    return new HashMap<>();
  }

  //todo:get all transactions between amounts x and y of a given account; being x and y pathParameters of the API
  //todo:filter transactions via repository not by service.
  //todo: should amounts type be changed? explain why
  //todo: use custom exceptions to throw errors and not exceptions thrown by java; and handle some of them through try and catch and some of them globally (ExceptionHandler)
  //todo: log different kind of levels depending of the information needed (errors, informative, and tracing) the header pass by sleuth should be present in all logs
  //todo: extra point should send an async message to any queue you prefer, this queue must only log the tracing information of the account that is being searched
  //todo: use "mapResponse" of "serviceMapper" in service layer
  @Override
  public AccountResponseDto getFilteredTransactions(UUID accountId, double minimum,
      double maximum) {
    return new AccountResponseDto();
  }
}

package com.pichincha.backend.test.iservices;

import com.pichincha.backend.test.dto.AccountResponseDto;
import com.pichincha.backend.test.dto.NewTransactionDto;
import com.pichincha.backend.test.dto.TransactionDto;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface ITransactionService {

  Map<String, List<TransactionDto>> getTransactionsForAccount(UUID accountId);

  AccountResponseDto getFilteredTransactions(UUID accountId, double minimum,
      double maximum);

  UUID addTransaction(NewTransactionDto newTransactionDto, String accountId);
}

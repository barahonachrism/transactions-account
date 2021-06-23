package com.pichincha.backend.test.mapper;

import com.pichincha.backend.test.dto.AccountDto;
import com.pichincha.backend.test.dto.NewTransactionDto;
import com.pichincha.backend.test.dto.TransactionDto;
import com.pichincha.backend.test.dto.TransactionResponseDto;
import com.pichincha.backend.test.dto.rest.RestTransaction;
import com.pichincha.backend.test.model.Account;
import com.pichincha.backend.test.model.Transaction;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.UUID;

@Mapper(imports = UUID.class)
public interface IServiceMapper {

    IServiceMapper INSTANCE = Mappers.getMapper(IServiceMapper.class);

    AccountDto accountToAccountDto(Account account);

    @Mapping(target="accountId", expression = "java( UUID.fromString(newTransactionDto.getAccountId()) )")
    Transaction newTransactionDtoToTransaction(NewTransactionDto newTransactionDto);

    TransactionDto transactionToTransactionDto(Transaction transaction);

    TransactionResponseDto transactionToTransactionResponseDto(Transaction transaction);

    List<TransactionDto> transactionListToTransactionDtoList(List<Transaction> transactions);

    List<TransactionResponseDto> transactionListToTransactionResponseDtoList(List<Transaction> transactions);

    RestTransaction transactionToRestTransaction(Transaction transaction);

    List<RestTransaction> transactionListToRestTransactionList(List<Transaction> transaction);

}

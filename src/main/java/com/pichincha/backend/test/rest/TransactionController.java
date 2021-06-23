package com.pichincha.backend.test.rest;

import com.pichincha.backend.test.dto.AccountResponseDto;
import com.pichincha.backend.test.dto.NewTransactionDto;
import com.pichincha.backend.test.dto.rest.RestStatus;
import com.pichincha.backend.test.dto.rest.RestTransaction;
import com.pichincha.backend.test.dto.rest.TransactionContainer;
import com.pichincha.backend.test.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RestController("/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<String> createTransaction(@RequestBody NewTransactionDto newTransactionDto){
        UUID transactionId = transactionService.addTransaction(newTransactionDto);
        return new ResponseEntity<>(transactionId.toString(), HttpStatus.resolve(201));
    }

    @GetMapping(value = "/transactions/{accountId}")
    public ResponseEntity<TransactionContainer> getAllTransactionsByAccountAndAmount(@PathVariable String accountId, HttpServletRequest request){
        AccountResponseDto accountResponseDto = new AccountResponseDto();
        String minAmountString = request.getParameter("minAmount");
        String maxAmountString = request.getParameter("maxAmount");

        Double minAmount =  minAmountString == null ? null : Double.valueOf(minAmountString);
        Double maxAmount =  maxAmountString == null ? null : Double.valueOf(maxAmountString);
        RestStatus status = new RestStatus();
        status.setCode(HttpStatus.OK.value());
        status.setErrorMessage("");
        HttpStatus httpStatus = HttpStatus.OK;

        TransactionContainer transactionContainer = new TransactionContainer();

        if(minAmount ==null && maxAmount == null){
            accountResponseDto = transactionService.getTransactionsForAccount(UUID.fromString(accountId));
        }
        else if(minAmount !=null && maxAmount != null){
            accountResponseDto = transactionService.getFilteredTransactions(UUID.fromString(accountId),minAmount,maxAmount);
        }
        else {
            status.setCode(400);
            status.setErrorMessage("El monto mínimo y monto máximo es requerido para la consulta");
            transactionContainer.setStatus(status);
            httpStatus = HttpStatus.BAD_REQUEST;
        }

        transactionContainer.setAccountTransactions(accountResponseDto);

        transactionContainer.setStatus(status);

        return new ResponseEntity<>(transactionContainer,httpStatus);
    }
}

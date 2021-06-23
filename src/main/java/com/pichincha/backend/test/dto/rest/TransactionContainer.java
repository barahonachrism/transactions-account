package com.pichincha.backend.test.dto.rest;

import java.util.List;

import com.pichincha.backend.test.dto.AccountResponseDto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class TransactionContainer {
  private AccountResponseDto accountTransactions;
  private RestStatus status;

}

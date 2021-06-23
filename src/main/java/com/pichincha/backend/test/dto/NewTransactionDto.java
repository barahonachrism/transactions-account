package com.pichincha.backend.test.dto;

import com.pichincha.backend.test.model.Transaction;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
public class NewTransactionDto {

  @NotNull
  private String accountId;

  @NotNull
  private String type;

  @NotNull
  private String comment;

  @NotNull
  private double amount;

}

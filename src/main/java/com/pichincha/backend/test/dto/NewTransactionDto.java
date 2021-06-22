package com.pichincha.backend.test.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class NewTransactionDto {

  @NotNull
  private String type;

  @NotNull
  private String comment;

  @NotNull
  private double amount;
}

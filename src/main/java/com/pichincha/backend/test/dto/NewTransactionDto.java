package com.pichincha.backend.test.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewTransactionDto {

  private String type;

  private String comment;

  private int amount;
}

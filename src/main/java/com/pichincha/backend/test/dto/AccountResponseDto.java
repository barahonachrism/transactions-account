package com.pichincha.backend.test.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class AccountResponseDto {

  String account;
  List<TransactionResponseDto> transactions;

}

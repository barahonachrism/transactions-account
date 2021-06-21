package com.pichincha.backend.test.dto;

import java.util.List;

//todo: if you know lombok, use annotations if not use traditional way or implement anything you know besides this 2
//todo: modify anything you need here to fulfill the requirements of the exercise
public class AccountResponseDto {

  String account;
  List<TransactionResponseDto> transactions;

}

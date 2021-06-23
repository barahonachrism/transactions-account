package com.pichincha.backend.test.service;


import com.pichincha.backend.test.dto.AccountDto;
import com.pichincha.backend.test.iservices.IAccountService;
import com.pichincha.backend.test.mapper.IServiceMapper;
import com.pichincha.backend.test.model.Account;
import com.pichincha.backend.test.repository.IAccountRepository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountService implements IAccountService {

  @Autowired
  private IAccountRepository accountRepository;

  public AccountDto getAccount(String accountNumber) {
    Account account = accountRepository.findByNumber(accountNumber);
    return IServiceMapper.INSTANCE.accountToAccountDto(account);
  }

}

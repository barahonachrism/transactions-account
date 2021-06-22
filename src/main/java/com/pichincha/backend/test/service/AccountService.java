package com.pichincha.backend.test.service;


import static lombok.AccessLevel.PRIVATE;

import com.pichincha.backend.test.dto.AccountDto;
import com.pichincha.backend.test.dto.NewTransactionDto;
import com.pichincha.backend.test.iservices.IAccountService;
import com.pichincha.backend.test.mapper.ServiceMapper;
import com.pichincha.backend.test.repository.IAccountRepository;
import java.util.UUID;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountService implements IAccountService {

  @Autowired
  private IAccountRepository accountRepository;

  @Autowired
  private ServiceMapper serviceMapper;

  public AccountDto getAccount(UUID id) {
    return serviceMapper.getAccountDto(accountRepository.findById(id));
  }
}

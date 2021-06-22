package com.pichincha.backend.test.repository;

import com.pichincha.backend.test.iservices.IAccountService;
import com.pichincha.backend.test.model.Account;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAccountRepository extends JpaRepository<Account, UUID> {

}

package com.pichincha.backend.test.repository.impl;

import com.pichincha.backend.test.model.Account;
import com.pichincha.backend.test.repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.UUID;

@Repository
public class AccountRepository extends SimpleJpaRepository<Account, UUID> implements IAccountRepository{

    public AccountRepository(@Autowired EntityManager entityManager) {
        super(Account.class,entityManager);
    }
}

package com.pichincha.backend.test.repository.impl;

import com.pichincha.backend.test.model.Account;
import com.pichincha.backend.test.repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.UUID;

@Repository
public class AccountRepository extends SimpleJpaRepository<Account, UUID> implements IAccountRepository{
    private EntityManager entityManager;

    public AccountRepository(@Autowired EntityManager entityManager) {
        super(Account.class,entityManager);
        this.entityManager = entityManager;
    }

    public Account findByNumber(String accountNumber){
        Query query = entityManager.createNamedQuery("account.findByNumber");
        query.setParameter("accountNumber",accountNumber);
        return (Account) query.getSingleResult();
    }
}

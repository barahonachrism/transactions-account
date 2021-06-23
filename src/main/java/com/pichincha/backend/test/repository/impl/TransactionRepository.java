package com.pichincha.backend.test.repository.impl;

import com.pichincha.backend.test.model.Transaction;
import com.pichincha.backend.test.repository.ITransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.UUID;

@Repository
public class TransactionRepository extends SimpleJpaRepository<Transaction, UUID> implements ITransactionRepository {
    private EntityManager entityManager;
    public TransactionRepository(EntityManager em) {
        super(Transaction.class, em);
        this.entityManager = em;
    }

    public List<Transaction> findTransactionsByAccount(UUID accountId){
        Query query = entityManager.createNamedQuery("transaction.findByAccountAndOrderedAmountDesc");
        query.setParameter("accountId", accountId);
        return query.getResultList();
    }

    public List<Transaction> findTransactionsByAccountIdAndAmounts(UUID accountId, double minAmount, double maxAmount){
        Query query = entityManager.createNamedQuery("transaction.findByAcountAndRangeAmount");
        query.setParameter("accountId", accountId);
        query.setParameter("minAmount",minAmount);
        query.setParameter("maxAmount", maxAmount);
        return query.getResultList();
    }
}

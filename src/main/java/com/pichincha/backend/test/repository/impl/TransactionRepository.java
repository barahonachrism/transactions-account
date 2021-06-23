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

    //todo:get all transactions between amounts x and y of a given account; being x and y pathParameters of the API
    //todo:filter transactions via repository not by service.

    public List<Transaction> customFindTransactionsByAmounts(UUID accountId, double minAmount, double maxAmount){
        Query query = entityManager.createQuery("select t from Transaction t where accountId = :accountId amount >= :minAmount and amount <= : maxAmount");
        query.setParameter("accountId", accountId);
        query.setParameter("minAmount",minAmount);
        query.setParameter("maxAmount", maxAmount);
        return query.getResultList();
    }
}

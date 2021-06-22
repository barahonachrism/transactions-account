package com.pichincha.backend.test.repository;

import com.pichincha.backend.test.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ITransactionRepository extends JpaRepository<Transaction, UUID> {
    List<Transaction> findTransactionsByAmounts(UUID accountId, double minAmount, double maxAmount);
}

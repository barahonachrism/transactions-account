package com.pichincha.backend.test.repository;

import com.pichincha.backend.test.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.UUID;
@NoRepositoryBean
public interface ITransactionRepository extends JpaRepository<Transaction, UUID> {
    List<Transaction> findTransactionsByAccountIdAndAmounts(UUID accountId, double minAmount, double maxAmount);
    List<Transaction> findTransactionsByAccount(UUID accountId);
}

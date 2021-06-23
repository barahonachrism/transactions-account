package com.pichincha.backend.test.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

import static javax.persistence.GenerationType.AUTO;

@NamedQueries({
        @NamedQuery(name = "transaction.findByAcountAndRangeAmount", query = "select t from Transaction t where t.accountId = :accountId and t.amount >= :minAmount and t.amount <= : maxAmount order by t.amount desc"),
        @NamedQuery(name = "transaction.findByAccountAndOrderedAmountDesc", query = "select t from Transaction t where t.accountId = :accountId order by t.amount desc")
}
)
@Entity
@Table(name="transaction")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = AUTO)
    @NotNull
    @Column(name = "id")
    private UUID id;

    @Column(name = "amount")
    @NotNull
    private Double amount;

    @Column(name = "type")
    @NotNull
    private String type;

    @Column(name = "creation_date")
    @NotNull
    private LocalDateTime creationDate;

    @Column(name = "account_id")
    @NotNull
    private UUID accountId;

    @Column(name = "comment")
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id",referencedColumnName = "id",insertable = false,updatable = false)
    private Account account;

    public UUID getAccountId() {
        return accountId;
    }

    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }
}

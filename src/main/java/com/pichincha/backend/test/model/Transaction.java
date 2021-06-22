package com.pichincha.backend.test.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.sql.Timestamp;
import java.util.UUID;

import static javax.persistence.GenerationType.AUTO;

/*
id uuid NOT NULL default random_uuid(),
    amount double precision NOT NULL,
    type character varying(70) NOT NULL,
    creation_date timestamp with time zone NOT NULL DEFAULT CURRENT_TIMESTAMP,
    account_id uuid,
    comment character varying(120) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT account_fk FOREIGN KEY (account_id)
        REFERENCES account (id)*/
@Entity
@Table(name="transaction")
@Getter
@Setter
@AllArgsConstructor
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
    private Timestamp creationDate;

    @Column(name = "account_id")
    @NotNull
    private UUID accountId;

    @Column(name = "comment")
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id",referencedColumnName = "id",insertable = false,updatable = false)
    private Account account;

}

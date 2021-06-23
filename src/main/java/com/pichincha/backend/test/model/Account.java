package com.pichincha.backend.test.model;

import static javax.persistence.GenerationType.AUTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NamedQueries({
        @NamedQuery(name="account.findByNumber", query = "select a from Account a where a.number = :accountNumber")
})
@Table(name="account")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Account {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = AUTO)
  UUID id;

  @NotNull
  @Column(name = "\"number\"")
  String number;

  @NotNull
  @Column(name="type", length = 70)
  String type;

  @NotNull
  @Column(name = "creation_date", insertable = false, updatable = false)
  LocalDateTime creationDate;

  @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
  private List<Transaction> transactionList;

}

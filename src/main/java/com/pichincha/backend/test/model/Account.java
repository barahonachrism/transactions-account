package com.pichincha.backend.test.model;

import static javax.persistence.GenerationType.AUTO;

import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
//todo: map relation of account with transaction based on migration
public class Account {

  @Id
  @GeneratedValue(strategy = AUTO)
  UUID id;
  String number;
  @Column(length = 70)
  String type;
  @Column(name = "creation_date", insertable = false, updatable = false)
  LocalDateTime creationDate;
}

package com.pichincha.backend.test.dto;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Builder
@Setter
@NoArgsConstructor
public class TransactionDto {

  private UUID id;

  private String comment;

  private String type;

  private LocalDateTime creationDate;

  private int amount;

}

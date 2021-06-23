package com.pichincha.backend.test.dto;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
@Builder
@Setter
@NoArgsConstructor
public class TransactionDto {

  @NotNull
  private UUID id;

  @NotNull
  private String comment;

  @NotNull
  private String type;

  @NotNull
  private LocalDateTime creationDate;

  @NotNull
  private double amount;

}

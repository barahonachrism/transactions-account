package com.pichincha.backend.test.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class AccountDto {

	@NotNull
	private UUID id;

	@NotNull
	private final String number;

	@NotNull
	private final String type;

	@NotNull
	private final LocalDateTime creationDate;

}

package com.pichincha.backend.test.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class AccountDto {

	@NotNull
	private final String number;

	@NotNull
	private final String type;

	@NotNull
	private final LocalDateTime creationDate;

}

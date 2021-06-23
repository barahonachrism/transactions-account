package com.pichincha.backend.test.dto.rest;

import static lombok.AccessLevel.PRIVATE;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
public class RestStatus {
  private int code;
  private String errorMessage;
  public RestStatus(){

  }
}

package com.pichincha.backend.test.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DetailMessageResponse {
    private String field;
    private String message;
}

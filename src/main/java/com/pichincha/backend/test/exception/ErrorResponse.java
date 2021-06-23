package com.pichincha.backend.test.exception;

import lombok.Data;

import java.util.List;

@Data
public class ErrorResponse {
    private int code;
    private String error;
    private List<DetailMessageResponse> messages;

    public ErrorResponse(ErrorEnum errorEnum){
        this.code = errorEnum.code;
        this.error = errorEnum.message;
    }

    public ErrorResponse(int code, String error){
        this.code = code;
        this.error = error;
    }
}

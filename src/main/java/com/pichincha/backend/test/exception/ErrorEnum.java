package com.pichincha.backend.test.exception;

public enum ErrorEnum {
    INTERNAL_SERVER_ERROR(500,"error.500"),
    NOT_FOUND(404,"error.404"),
    BAD_REQUEST(400,"error.400");
    ErrorEnum(int code, String message){
        this.code = code;
        this.message = message;
    }
    public final int code;
    String message;
}

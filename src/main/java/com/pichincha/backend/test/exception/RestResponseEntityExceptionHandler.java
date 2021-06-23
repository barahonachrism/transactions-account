package com.pichincha.backend.test.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @Autowired
    private MessageSource messageSource;

    /**
     * Permite manejar excepciones de forma generica
     *
     * @param ex
     * @param webRequest
     * @return
     */
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleAllExceptions(RuntimeException ex, WebRequest webRequest) {
        //Obtener el mensaje internacionalizado
        String message = messageSource.getMessage(ErrorEnum.INTERNAL_SERVER_ERROR.message, null, LocaleContextHolder.getLocale());
        ErrorResponse error = new ErrorResponse(ErrorEnum.INTERNAL_SERVER_ERROR.code, message);
        log.error("Error en el ENDPOINT: ", ex);
        return handleExceptionInternal(ex, error, null, HttpStatus.INTERNAL_SERVER_ERROR, webRequest);
    }

    /**
     * Permite manejar errores cuando no se envia ningun dato que se pueda validar, es decir un body vacio
     * @param ex
     * @param headers
     * @param status
     * @param webRequest
     * @return
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest webRequest) {
        String message = messageSource.getMessage(ErrorEnum.BAD_REQUEST.message,null,LocaleContextHolder.getLocale());
        ErrorResponse error = new ErrorResponse(ErrorEnum.BAD_REQUEST.code,message);
        log.error("Error: ", ex);
        return handleExceptionInternal(ex, error, headers, HttpStatus.BAD_REQUEST, webRequest);
    }

    /**
     * Permitir una respuesta personalizada en el manejo de errores
     * @param ex
     * @param headers
     * @param status
     * @param webRequest
     * @return
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest webRequest) {

        List<DetailMessageResponse> messages = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new DetailMessageResponse(error.getField(),error.getDefaultMessage()))
                .collect(Collectors.toList());
        String message = messageSource.getMessage(ErrorEnum.BAD_REQUEST.message,null,LocaleContextHolder.getLocale());
        ErrorResponse error = new ErrorResponse(ErrorEnum.BAD_REQUEST.code,message);
        error.setMessages(messages);
        log.error("Error: ", ex);
        return new ResponseEntity<>(error, headers, HttpStatus.BAD_REQUEST);
    }
}

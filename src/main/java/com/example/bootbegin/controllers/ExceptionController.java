package com.example.bootbegin.controllers;

import com.example.bootbegin.dto.response.ExceptionResponse;
import com.example.bootbegin.exception.LongDurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    Logger logger = LoggerFactory.getLogger(ExceptionController.class); /* If there is no Lombok */

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        FieldError fieldError = ex.getBindingResult().getFieldError();
        String message = "Object"
                + fieldError.getObjectName()
                + ", field: "
                + fieldError.getField()
                + " - "
                + fieldError.getDefaultMessage();
        logger.warn("Handling handlerMethodArgumentNotValidException" + message);
        return new ExceptionResponse(HttpStatus.BAD_REQUEST.value(), "invalid input Data", message);
    }

    @ExceptionHandler(LongDurationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleLongDurationException(LongDurationException ex) {
        return new ExceptionResponse(404, "invalid input data", ex.getMessage());
    }
}

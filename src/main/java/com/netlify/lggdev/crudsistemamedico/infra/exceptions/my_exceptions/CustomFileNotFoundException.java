package com.netlify.lggdev.crudsistemamedico.infra.exceptions.my_exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class CustomFileNotFoundException extends RuntimeException{
    public CustomFileNotFoundException(String message) {
        super(message);
    }
    public CustomFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

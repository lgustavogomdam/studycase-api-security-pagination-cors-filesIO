package com.netlify.lggdev.crudsistemamedico.infra.exceptions.my_exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.naming.AuthenticationException;
@ResponseStatus(HttpStatus.FORBIDDEN)
public class InvalidJwtAuthenticationException extends AuthenticationException {
    private static final long SerialVersionUID = 1L;
    public InvalidJwtAuthenticationException(String message) {
        super(message);
    }
}

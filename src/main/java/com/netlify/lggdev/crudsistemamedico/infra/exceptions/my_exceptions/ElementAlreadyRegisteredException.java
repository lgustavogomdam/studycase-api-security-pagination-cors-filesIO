package com.netlify.lggdev.crudsistemamedico.infra.exceptions.my_exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ElementAlreadyRegisteredException extends RuntimeException {
    public ElementAlreadyRegisteredException(String message) {
        super(message);
    }
}

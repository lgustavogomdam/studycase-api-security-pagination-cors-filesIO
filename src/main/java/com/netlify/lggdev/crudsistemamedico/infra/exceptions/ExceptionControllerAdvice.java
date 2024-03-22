package com.netlify.lggdev.crudsistemamedico.infra.exceptions;

import com.netlify.lggdev.crudsistemamedico.infra.exceptions.my_exceptions.ElementAlreadyRegisteredException;
import com.netlify.lggdev.crudsistemamedico.infra.exceptions.my_exceptions.ExceptionResponse;
import com.netlify.lggdev.crudsistemamedico.infra.exceptions.my_exceptions.InvalidJwtAuthenticationException;
import com.netlify.lggdev.crudsistemamedico.infra.exceptions.my_exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class ExceptionControllerAdvice extends ResponseEntityExceptionHandler {

    //É usada para capturar todo tipo de exceção
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),
                ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //Tratamento de exceção especifica
    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleNotFoundExceptions(Exception ex, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),
                ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidJwtAuthenticationException.class)
    public final ResponseEntity<ExceptionResponse> handleInvalidJwtAuthenticationException(Exception ex, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),
                ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.FORBIDDEN);
    }
    @ExceptionHandler(ElementAlreadyRegisteredException.class)
    public final ResponseEntity<ExceptionResponse> handleElementAlreadyRegisteredException(Exception ex, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),
                ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.FORBIDDEN);
    }
}

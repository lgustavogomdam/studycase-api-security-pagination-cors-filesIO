package com.netlify.lggdev.crudsistemamedico.infra.exceptions.my_exceptions;

import java.io.Serializable;
import java.util.Date;

public class ExceptionResponse implements Serializable {
    private static final long SerialVersionUID = 1L;

    private Date timestamp;
    private String message;
    private String details;

    public ExceptionResponse(Date timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public Date getTimestamp() {
        return this.timestamp;
    }

    public String getMessage() {
        return this.message;
    }

    public String getDetails() {
        return this.details;
    }

}

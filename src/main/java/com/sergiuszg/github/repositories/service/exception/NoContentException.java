package com.sergiuszg.github.repositories.service.exception;

import org.springframework.http.HttpStatus;

public class NoContentException extends RuntimeException{

    private HttpStatus httpStatus;

    public NoContentException(String message) {
        super(message);
        this.httpStatus = HttpStatus.NO_CONTENT;
    }
}

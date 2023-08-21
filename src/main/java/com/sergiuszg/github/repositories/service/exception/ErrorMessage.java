package com.sergiuszg.github.repositories.service.exception;

import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public class ErrorMessage {
    private HttpStatus status;
    private String message;
}

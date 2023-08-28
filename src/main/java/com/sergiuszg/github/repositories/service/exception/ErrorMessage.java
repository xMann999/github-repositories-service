package com.sergiuszg.github.repositories.service.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Builder
@AllArgsConstructor
@Getter
public class ErrorMessage {
    private HttpStatus status;
    private String message;
}

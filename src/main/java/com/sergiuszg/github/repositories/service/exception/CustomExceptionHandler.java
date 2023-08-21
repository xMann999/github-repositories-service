package com.sergiuszg.github.repositories.service.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler({ResourceNotFoundException.class, NoContentException.class})
    public ResponseEntity<ErrorMessage> generalExeption(final ResourceNotFoundException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(e.getHttpStatus()).body(
                ErrorMessage.builder()
                        .message(e.getMessage())
                        .status(e.getHttpStatus())
                        .build());
    }

}

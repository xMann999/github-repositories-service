package com.sergiuszg.github.repositories.service.config;

import com.sergiuszg.github.repositories.service.exception.ResourceNotFoundException;
import feign.FeignException;
import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;
import org.springframework.stereotype.Component;

@Component
public class DefaultErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        FeignException exception = feign.FeignException.errorStatus(methodKey, response);
        int status = response.status();
        if (status >= 500) {
            return new RetryableException(
                    response.status(),
                    exception.getMessage(),
                    response.request().httpMethod(),
                    exception,
                    null,
                    response.request());
        }
//        if (status == 404) {
//            return new ResourceNotFoundException("Resource not found");
//        }
        return exception;
    }
}


package com.se.sample.rest.client.exception;

import com.se.sample.rest.client.model.error.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RestServerException extends RuntimeException {

    public RestServerException(ErrorResponse errorResponse) {
        super(errorResponse.getMessage());
    }
}

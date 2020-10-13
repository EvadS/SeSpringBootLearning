package com.se.sample.rest.client.component;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.*;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.net.URI;
import java.util.Set;

public class ValidatableRestTemplate extends RestTemplate {
    private final Validator validator;

    public ValidatableRestTemplate(Validator validator) {
        this.validator = validator;
    }

    @Override
    protected <T> T doExecute(URI url, HttpMethod method, RequestCallback requestCallback,
                              ResponseExtractor<T> responseExtractor) throws RestClientException {

        try {


            final T response = super.doExecute(url, method, requestCallback, responseExtractor);

            Object body;

            if (response instanceof ResponseEntity<?>) {
                body = ((ResponseEntity) response).getBody();
            } else {
                body = response;
            }

            // validation response here !!!
            final Set<ConstraintViolation<Object>> violations = validator.validate(body);
            if (violations.isEmpty()) {
                return response;
            }


            throw new ConstraintViolationException("Invalid response", violations);
        }catch (ResourceAccessException e)
        {
            //TODO: Here check server error

            int a =0;
           return null;
        }

    }
}


package com.se.sample.rest.client.component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.se.sample.rest.client.exception.ResourceUnavailableException;
import com.se.sample.rest.client.exception.RestServerException;
import com.se.sample.rest.client.model.error.ErrorResponse;
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
        } catch (ResourceAccessException e) {
            throw new ResourceUnavailableException(e.getMessage());
        } catch (HttpStatusCodeException ex) {

            ObjectMapper objectMapper = new ObjectMapper();
            String json = ex.getResponseBodyAsString();
            ErrorResponse errorResponse = null;
            try {
                errorResponse = objectMapper.readValue(json, ErrorResponse.class);
                // Have no time to use logger
                System.out.println("Error response: " + errorResponse);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            // TODO: throw my exception wit correct message from errorResponse
            throw new RestServerException(errorResponse);
        }
    }


}


package com.se.sample.rest.client.component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.se.sample.rest.client.model.error.ErrorResponse;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.*;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.io.*;
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
        }catch (HttpStatusCodeException ex) {

            ObjectMapper objectMapper = new ObjectMapper();
            String json = ex.getResponseBodyAsString();
            ErrorResponse errorResponse = null;
            try {
                //This is WORK
                errorResponse = objectMapper.readValue(json, ErrorResponse.class);
                System.out.println("Error response: " + errorResponse);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            Object response = new ResponseEntity<String>(ex.getResponseBodyAsString(), ex.getResponseHeaders(), ex.getStatusCode());
            // TODO: have no time to components binding
            System.out.println("Response : " +  response);
           return null;
        }
    }

    /* Serialize the object to byte array */
    private static byte[] getByteArray(Object obj) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try (ObjectOutputStream os = new ObjectOutputStream(bos)) {
            os.writeObject(obj);
        }
        return bos.toByteArray();
    }

    /* De serialize the byte array to object */
    private static Object getObject(byte[] byteArr) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bis = new ByteArrayInputStream(byteArr);
        ObjectInput in = new ObjectInputStream(bis);
        return in.readObject();
    }

}


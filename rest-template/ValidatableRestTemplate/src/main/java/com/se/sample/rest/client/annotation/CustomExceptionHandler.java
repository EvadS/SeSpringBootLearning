package com.se.sample.rest.client.annotation;


import com.se.sample.rest.client.exception.ResourceUnavailableException;
import com.se.sample.rest.client.exception.RestServerException;
import com.se.sample.rest.client.exception.TokenRefreshException;
import com.se.sample.rest.client.model.error.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unchecked","rawtypes"})
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler
{

    private final Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);


    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("Server Error", details);
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceUnavailableException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(ResourceUnavailableException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("Record Not Found", details);
        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(TokenRefreshException.class)
    public final ResponseEntity<Object> handleTokenRefreshException(TokenRefreshException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("Server unavailable.", details);
        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RestServerException.class)
    public final ResponseEntity<Object> handleRestServerException(RestServerException ex, WebRequest request) {

        List<String> details = new ArrayList<>();
        for(String error : ex.getErrorResponse().getDetails()) {
            details.add(error);
        }

        ErrorResponse error = new ErrorResponse("Incorrect request model from server.", details);
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<Object> handleTokenRefreshException(ConstraintViolationException ex, WebRequest request) {
        List<String> details = new ArrayList<>();

        for(ConstraintViolation error :  ex.getConstraintViolations()) {
            details.add(error.getMessage());
        }

        ErrorResponse error = new ErrorResponse("SIncorrect request model.", details);
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public String handleXXException(HttpClientErrorException e) {
        logger.error("*** log HttpClientErrorException: ", e);
        return "HttpClientErrorException_message";
    }

    @ExceptionHandler(HttpServerErrorException.class)
    public String handleXXException(HttpServerErrorException e) {
        logger.error("***log HttpServerErrorException: ", e);
        return "HttpServerErrorException_message";
    }






    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> details = new ArrayList<>();
        for(ObjectError error : ex.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }
        ErrorResponse error = new ErrorResponse("Validation Failed", details);
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }
}
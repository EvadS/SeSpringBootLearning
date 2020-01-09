package io.apptizer.api.errors.codes;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionMapperHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorInfo> resourceNotFound(ResourceNotFoundException ex) {
        ErrorInfo errorInfo = new ErrorInfo(ex.getApiErrors().getErrorCode(), ex.getMessage());
        return new ResponseEntity<>(errorInfo, HttpStatus.NOT_FOUND);
    }


    private class ErrorInfo {

       final  String errorCode;
       final String message;

        public ErrorInfo(String errorCode, String message) {
            this.errorCode = errorCode;
            this.message = message;
        }
    }
}

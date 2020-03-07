package com.example.filedemo.validator;

import com.example.filedemo.model.ResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.net.BindException;

@ControllerAdvice
public class APIExceptionHandler extends ResponseEntityExceptionHandler
{
    Logger log = LoggerFactory.getLogger(APIExceptionHandler.class);
//

    // Let Spring BasicErrorController handle the exception, we just override the status code
    @ExceptionHandler(BindException.class)
    public ResponseEntity<ResponseDTO> customHandleNotFound(Exception ex, WebRequest request) {

        ResponseDTO responseDTO = new  ResponseDTO("400",ex.getMessage());

 /*
         HttpHeaders headers = new HttpHeaders();

		if (ex instanceof HttpRequestMethodNotSupportedException) {
			HttpStatus status = HttpStatus.METHOD_NOT_ALLOWED;
			return handleHttpRequestMethodNotSupported((HttpRequestMethodNotSupportedException) ex, headers, status, request);
		}
		else if (ex instanceof HttpMediaTypeNotSupportedException) {
			HttpStatus status = HttpStatus.UNSUPPORTED_MEDIA_TYPE;
			return handleHttpMediaTypeNotSupported((HttpMediaTypeNotSupportedException) ex, headers, status, request);
		}
        * */

        return new ResponseEntity<>(responseDTO, HttpStatus.NOT_FOUND);

    }


    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception  ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
       int a=10;
       System.out.println("handleExceptionInternal ================================");
     //   return super.handleExceptionInternal(ex, body, headers, status, request);

        ResponseDTO responseDTO = new  ResponseDTO(
                status.toString(),
                (ex.getMessage()));




        return ResponseEntity.badRequest().body(responseDTO);

    }



    @Override
    protected ResponseEntity handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                          HttpHeaders headers, HttpStatus status, WebRequest request) {

        log.error(ex.getMessage(), ex);
        FieldError fieldError = ex.getBindingResult().getFieldError();
    //    ApiError responseDTO = new ApiError("111",111);
//                .status(status.toString())
//                .message(fieldError.getDefaultMessage()).build();

        ResponseDTO responseDTO = new  ResponseDTO(
                status.toString(),
                (fieldError.getDefaultMessage()));

        return ResponseEntity.badRequest().body(responseDTO);
    }
//
//    @ExceptionHandler(ConstraintViolationException.class)
//    public final ResponseEntity<Object> handleConstraintViolationException(Exception ex, WebRequest request) {
//
//        log.error(ex.getMessage(), ex);
//
//        ApiError responseDTO = new ApiError("111",111);
//
//           //     .status(HttpStatus.BAD_REQUEST.toString())
//           //     .message(ex.getMessage()).build();
//
//        return ResponseEntity.badRequest().body(responseDTO);
//    }
}
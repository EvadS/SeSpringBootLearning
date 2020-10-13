package com.se.sample.rest.client.controller;


import com.se.sample.rest.client.exception.TokenRefreshException;
import com.se.sample.rest.client.model.request.EmployeeRequest;
import com.se.sample.rest.client.model.response.EmployeeResponse;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

@RestController
@RequestMapping("/empl-client")
@Api(value = "Controller for communicate with Rest Template")
public class EmplClientController {

    private static final String emplUrl = "http://localhost:8001/empl/add";
    private static final String emplUrl2 = "http://localhost:8001/empl/add2";

    private final Logger logger = LoggerFactory.getLogger(EmplClientController.class);
    @Autowired
    RestTemplate restTemplate;

//
//    @GetMapping(value = "/{id}")
//    public ResponseEntity<EmployeeVO> getEmployeeById(@PathVariable("id") Long id) {
//        EmployeeVO employee = employeeService.getEmployeeById(id);
//
//        if (employee == null) {
//            throw new RecordNotFoundException("Invalid employee id : " + id);
//        }
//        return new ResponseEntity<EmployeeVO>(employee, HttpStatus.OK);
//    }

    @PostMapping(value = "/add")
    public ResponseEntity<EmployeeResponse> addEmployee(@Valid @RequestBody EmployeeRequest employeeRequest) {
      //  try {
            EmployeeRequest empl = new EmployeeRequest("email.com", "firstName", "lastName");
            ResponseEntity<EmployeeResponse> result = restTemplate.postForEntity(emplUrl, employeeRequest, EmployeeResponse.class);


            logger.info("response: " + result.getBody());
            return result;

//        } catch (HttpClientErrorException ex) {
//            logger.error("Session service not available {}");
//            // throw new TokenRefreshException(String.format("Error while validating session by token: %s", ex.getMessage()));
//            return null;
//
//       }
//        catch (final RestClientException | HttpMessageConversionException ex) {
//            logger.error("Session service not available {}");
//            throw new TokenRefreshException(String.format("Error while validating session by token: %s", ex.getMessage()));
//        }
//        catch (Exception ex) {
//            logger.error("Error while send  information:  {}", ex.getMessage());
//            // throw new TokenRefreshException(String.format("Error while validating session by token: %s", ex.getMessage()));
//            return null;
//
//        }
    }

    @PostMapping(value = "/add-exception")
    public ResponseEntity<EmployeeResponse> addEmployee2(@Valid @RequestBody EmployeeRequest employeeRequest) {
        //  try {
       ResponseEntity<EmployeeResponse> result = restTemplate.postForEntity(emplUrl2, employeeRequest, EmployeeResponse.class);


        logger.info("response: " + result.getBody());
        return result;
    }
}

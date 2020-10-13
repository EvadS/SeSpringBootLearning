package com.example.restserver.controller;

import com.example.restserver.service.EmployeeService;
import com.example.restserver.model.domain.EmployeeVO;
import com.example.restserver.exception.RecordNotFoundException;
import com.example.restserver.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/empl")
public class EmployeeController {

    private final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    EmployeeService employeeService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<EmployeeVO> getEmployeeById(@PathVariable("id") Long id) {
        EmployeeVO employee = employeeService.getEmployeeById(id);

        if (employee == null) {
            throw new RecordNotFoundException("Invalid employee id : " + id);
        }
        return new ResponseEntity<EmployeeVO>(employee, HttpStatus.OK);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<EmployeeVO> addEmployee(@Valid @RequestBody Employee employee) {

        EmployeeVO ent = new EmployeeVO();
        ent.setEmail(employee.getEmail());
        ent.setFirstName(employee.getFirstName());
        ent.setLastName(employee.getLastName());

        EmployeeVO res = employeeService.addEmployee(ent);

        return  ResponseEntity.ok(res);
    }
}

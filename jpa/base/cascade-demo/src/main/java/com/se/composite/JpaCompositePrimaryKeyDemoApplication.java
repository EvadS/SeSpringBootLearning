package com.se.composite;

import com.se.composite.model.Employee;
import com.se.composite.model.EmployeeIdentity;
import com.se.composite.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpaCompositePrimaryKeyDemoApplication implements CommandLineRunner {

    @Autowired
    private EmployeeRepository employeeRepository;

    public static void main(String[] args) {
        SpringApplication.run(JpaCompositePrimaryKeyDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Cleanup employees table
        employeeRepository.deleteAllInBatch();

        // Insert a new Employee in the database
        Employee employee = new Employee(new EmployeeIdentity("E-123", "D-457"),
                "Rajeev Kumar Singh",
                "rajeev@callicoder.com",
                "+91-9999999999");

        employeeRepository.save(employee);
    }
}
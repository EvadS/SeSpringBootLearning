package se.aop.exceptionhandling;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import se.aop.exceptionhandling.service.ExceptionalService;

@SpringBootApplication
@EnableAspectJAutoProxy
public class AopExampleApp implements CommandLineRunner {

    @Autowired
    ExceptionalService service;

    public static void main(String[] args) {
        SpringApplication.run(AopExampleApp.class);
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            service.throwException();
        } catch (Exception ex) {

        }
    }
}
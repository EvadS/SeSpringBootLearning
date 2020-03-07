package com.example.filedemo;


import com.example.filedemo.model.Person;
import org.junit.Assert;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class DemoJValidationApplicationTests {
    // Инициализация Validator
    private static Validator validator;
    static {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.usingContext().getValidator();
    }

    @Test
    public void testValidators() {
        final Person person = new Person("Иван Петров", -4500);

        Set<ConstraintViolation<Person>> validates = validator.validate(person);
        Assert.assertTrue(validates.size() > 0);
        validates.stream().map(v -> v.getMessage())
                .forEach(System.out::println);
    }
}

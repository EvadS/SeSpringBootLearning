package com.se.sample.rest.client.config;


import com.se.sample.rest.client.component.RestTemplateResponseErrorHandler;
import com.se.sample.rest.client.component.ValidatableRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import javax.validation.Validator;

@Configuration
public class BeansConfiguration {


    @Bean
    RestTemplate restTemplate(Validator validator) {

        ValidatableRestTemplate validatableRestTemplate = new ValidatableRestTemplate(validator);
       // validatableRestTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());

        return validatableRestTemplate;
    }
}

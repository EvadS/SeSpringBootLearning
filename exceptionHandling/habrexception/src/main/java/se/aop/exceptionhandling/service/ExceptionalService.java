package se.aop.exceptionhandling.service;

import org.springframework.stereotype.Service;

@Service
public class ExceptionalService {
    public void throwException() throws Exception {
        throw new Exception();
    }
}
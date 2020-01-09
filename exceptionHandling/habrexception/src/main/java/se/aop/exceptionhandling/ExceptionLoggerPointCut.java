package se.aop.exceptionhandling;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
@Slf4j
public class ExceptionLoggerPointCut {
    @AfterThrowing(pointcut = "execution(* se.aop.exceptionhandling.*.*.*(..))", throwing = "ex")
    public void logError(Exception ex) {
        log.info("-----------Log from ExceptionLoggerPointCut ----");
        ex.printStackTrace();
    }
}
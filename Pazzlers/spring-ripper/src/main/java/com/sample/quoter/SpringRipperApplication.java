package com.sample.quoter;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringRipperApplication {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        context.getBean(TerminatorQuoter.class).sayQuotes();
    }
}

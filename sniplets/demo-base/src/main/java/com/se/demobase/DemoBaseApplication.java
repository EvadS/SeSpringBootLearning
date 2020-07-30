package com.se.demobase;

import com.se.demobase.component.HelloWorld;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class DemoBaseApplication implements CommandLineRunner {

	@Autowired
	HelloWorld helloWorldComponent;

	@Value("${lhs}")
	int lhs;

	public static void main(String[] args) {

		var ctx = SpringApplication.run(DemoBaseApplication.class, args);

		System.out.println("# Beans: " + ctx.getBeanDefinitionCount());
		var names = ctx.getBeanDefinitionNames();
		Arrays.sort(names);
		Arrays.asList(names).forEach(System.out::println);
	}

	@Override
	public void run(String... args) {
		System.out.println("Inside command line runner .... ");


	}
}

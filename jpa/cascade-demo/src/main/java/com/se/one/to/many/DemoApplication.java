package com.se.one.to.many;

import com.se.one.to.many.entity.Address;
import com.se.one.to.many.entity.Person;
import com.se.one.to.many.repository.AddressRepository;
import com.se.one.to.many.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.w3c.dom.css.CSSFontFaceRule;

import java.util.Arrays;


@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    AddressRepository addressRepository;
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        addressRepository.deleteAllInBatch();
        personRepository.deleteAllInBatch();

        Address address = new Address();
        address.setBuilding("2");
        address.setCity("moscow");
        address.setStreet("Lenin");

        Person person = new Person();
        person.setAddress((address));
        person.setName("Person name ");
        address.setTenants(Arrays.asList(person));
        addressRepository.save(address);

        personRepository.save(person);



        Person person2 = new Person();
        person2.setName("Person name 2");
        personRepository.save(person2);

        address.setTenants(Arrays.asList(person,person2));
        addressRepository.save(address);

    }
}

package com.se.semple.controller;

/**
 * Created by Evgeniy Skiba on 21.04.21
 */

import com.se.semple.entity.Person;
import com.se.semple.exception.MyEntityNotFoundException;
import com.se.semple.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping
    public List<Person> listAllPersons() {
        List<Person> persons = personRepository.findAll();
        return persons;
    }

    @GetMapping(value = "/{personId}")
    public Person getPerson(@PathVariable("personId") long personId) {
        return personRepository.findById(personId).orElseThrow(() -> new MyEntityNotFoundException(personId));
    }



    @PostMapping
    public Person createPerson(@RequestBody Person person) {
        return personRepository.save(person);
    }

    @PutMapping("/{id}")
    public Person updatePerson(@RequestBody Person person, @PathVariable long id) {
        Person oldPerson = personRepository.getOne(id);
        oldPerson.setName(person.getName());
        return personRepository.save(oldPerson);
    }
}
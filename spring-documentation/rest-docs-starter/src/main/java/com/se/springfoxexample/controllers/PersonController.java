package com.se.springfoxexample.controllers;

import com.se.springfoxexample.services.PersonService;
import com.se.springfoxexample.domain.Person;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v2/persons/")
@Api(description = "Set of endpoints for Creating, Retrieving, Updating and Deleting of Persons.")
public class PersonController {

    private PersonService personService;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @ApiOperation("Returns list of all Persons in the system.")
    public List getAllPersons() {
        return personService.getAllPersons();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}", produces = "application/json")
    @ApiOperation(value = "Returns a specific person by their identifier.", notes = "Returns a single contact", tags = { "my-tag" })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response=Person.class )  })

    public Person getPersonById(@ApiParam("Id of the person to be obtained. Cannot be empty.")
                                @PathVariable int id) {
        return personService.getPersonById(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    @ApiOperation(value="Deletes a person from the system. 404 if the person's identifier is not found.",tags = { "my-tag" })
    public void deletePerson(@ApiParam("Id of the person to be deleted. Cannot be empty.")
                             @PathVariable int id) {
        personService.deletePerson(id);
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    @ApiOperation(value = "Creates a new person.", tags = { "my-tag" })
    public Person createPerson(@ApiParam("Person information for a new person to be created.")
                               @RequestBody Person person) {
        return personService.createPerson(person);
    }

    @Autowired
    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    int a =0;
}

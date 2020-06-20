package com.se.example.controller;

/**
 * @author Evgeniy Skiba on 20.06.2020
 * @project spring-data-jpa
 */

import com.se.example.controller.dto.UserListRequest;
import com.se.example.service.UserService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * localhost:8080/user?city=buda&street=paladino&search=john&sort=firstName,asc&page=0&size=2
     */
    @RequestMapping(
            value = "",
            method = RequestMethod.GET)
    public ResponseEntity getAllUser(UserListRequest request, Pageable pageable) {
        return userService.findAll(request, pageable).fold(
                this::errorToResponse,
                this::toResponse
        );
    }
}

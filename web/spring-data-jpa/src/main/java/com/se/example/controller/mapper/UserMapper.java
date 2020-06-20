package com.se.example.controller.mapper;

/**
 * @author Evgeniy Skiba on 20.06.2020
 * @project spring-data-jpa
 */

import com.se.example.controller.dto.UserDto;
import com.se.example.domain.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto map(User user);

    List<UserDto> map(List<User> users);
}

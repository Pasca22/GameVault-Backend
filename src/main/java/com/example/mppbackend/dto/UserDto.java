package com.example.mppbackend.dto;

import com.example.mppbackend.entity.User;

import java.util.List;


public class UserDto {
    public Long id;
    public String username;
    public String email;
    public List<String> roles;

    public static UserDto UserDtoFromUser(User user) {
        UserDto userDto = new UserDto();
        userDto.id = user.getId();
        userDto.username = user.getUsername();
        userDto.email = user.getEmail();
        userDto.roles = user.getRoles().stream().map(role -> role.getName().name()).toList();
        return userDto;
    }
}

package com.example.mppbackend.mapper;

import com.example.mppbackend.dto.UserDto;
import com.example.mppbackend.entity.User;

public class UserMapper {
    public static UserDto mapToUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getAvatar(),
                user.getIp());
    }

    public static User mapToUser(UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getUsername(),
                userDto.getPassword(),
                userDto.getEmail(),
                userDto.getAvatar(),
                userDto.getIp());
    }
}

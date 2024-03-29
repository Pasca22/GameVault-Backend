package com.example.mppbackend.service;

import com.example.mppbackend.dto.UserDto;
import com.example.mppbackend.entity.User;
import com.example.mppbackend.mapper.UserMapper;
import com.example.mppbackend.repository.UserRepository;
import com.example.mppbackend.validation.UserValidation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository = new UserRepository();

    public UserDto addUser(UserDto userDto) {
        User user = UserMapper.mapToUser(userDto);
        if (userRepository.existsById(user.getId())) {
            throw new RuntimeException("User with id " + user.getId() + " already exists");
        }
        UserValidation.validate(user);

        userRepository.add(user);
        return userDto;
    }

    public UserDto getUserById(Integer id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User with id " + id + " not found");
        }
        User user = userRepository.getById(id);
        return UserMapper.mapToUserDto(user);
    }

    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.getAll();
        return users.stream().map(UserMapper::mapToUserDto).toList();
    }

    public UserDto updateUser(Integer id, UserDto userDto) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User with id " + id + " not found");
        }
        User updatedUser = UserMapper.mapToUser(userDto);

        UserValidation.validate(updatedUser);

        userRepository.update(id, updatedUser);
        User currentUser = userRepository.getById(id);

        return UserMapper.mapToUserDto(currentUser);
    }

    public void deleteUser(Integer id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User with id " + id + " not found");
        }
        userRepository.delete(id);
    }
}

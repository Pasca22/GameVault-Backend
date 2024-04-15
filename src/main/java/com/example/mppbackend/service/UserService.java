package com.example.mppbackend.service;

import com.example.mppbackend.entity.User;
import com.example.mppbackend.repository.UserRepository;
import com.example.mppbackend.validation.UserValidation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public User addUser(User user) {
        UserValidation.validate(user);
        userRepository.save(user);
        return user;
    }

    public User getUserById(Long id) {
        if (userRepository.findById(id).isEmpty()) {
            throw new RuntimeException("User with id " + id + " not found");
        }
        return userRepository.findById(id).get();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(Long id, User updatedUser) {
        if (userRepository.findById(id).isEmpty()) {
            throw new RuntimeException("User with id " + id + " not found");
        }

        UserValidation.validate(updatedUser);

        User user = userRepository.findById(id).get();

        user.setUsername(updatedUser.getUsername());
        user.setEmail(updatedUser.getEmail());
        user.setPassword(updatedUser.getPassword());
        user.setIp(updatedUser.getIp());
        user.setAvatar(updatedUser.getAvatar());

        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        if (userRepository.findById(id).isEmpty()) {
            throw new RuntimeException("User with id " + id + " not found");
        }
        userRepository.deleteById(id);
    }
}

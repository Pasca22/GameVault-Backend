package com.example.mppbackend.service;

import com.example.mppbackend.dto.UserDto;
import com.example.mppbackend.entity.GameOrder;
import com.example.mppbackend.entity.User;
import com.example.mppbackend.repository.GameOrderRepository;
import com.example.mppbackend.repository.RoleRepository;
import com.example.mppbackend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GameOrderRepository gameOrderRepository;

    @Autowired
    private RoleRepository roleRepository;

    public List<GameOrder> getGameOrders(Long id) {
        User user = userRepository.findById(id).get();
        return user.getGameOrders();
    }

    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserDto::UserDtoFromUser).filter(userDto -> !userDto.roles.contains("ROLE_ADMIN")).toList();
    }

    public List<GameOrder> getAllGameOrders() {
        return gameOrderRepository.findAll();
    }

    public void deleteUser(Long id) {
        if (userRepository.findById(id).isEmpty()) {
            throw new RuntimeException("User with id " + id + " not found");
        }
        roleRepository.deleteUserRolesByUserId(id);
        userRepository.deleteById(id);
    }

    public UserDto updateUser(Long id, User user) {
        if (userRepository.findById(id).isEmpty()) {
            throw new RuntimeException("User with id " + id + " not found");
        }
        User updatedUser = userRepository.findById(id).get();
        updatedUser.setUsername(user.getUsername());
        updatedUser.setEmail(user.getEmail());
        userRepository.save(updatedUser);
        return UserDto.UserDtoFromUser(updatedUser);
    }

    public GameOrder addGameOrder(Long userId, GameOrder gameOrder) {
        User user = userRepository.findById(userId).get();
        gameOrder.setUser(user);
        return gameOrderRepository.save(gameOrder);
    }

    public void deleteGameOrder(Long id) {
        if (gameOrderRepository.findById(id).isEmpty()) {
            throw new RuntimeException("Game order with id " + id + " not found");
        }
        gameOrderRepository.deleteById(id);
    }

    public GameOrder updateGameOrder(Long id, GameOrder gameOrder) {
        if (gameOrderRepository.findById(id).isEmpty()) {
            throw new RuntimeException("Game order with id " + id + " not found");
        }
        GameOrder updatedGameOrder = gameOrderRepository.findById(id).get();
        updatedGameOrder.setName(gameOrder.getName());
        updatedGameOrder.setPrice(gameOrder.getPrice());
        updatedGameOrder.setDescription(gameOrder.getDescription());
        return gameOrderRepository.save(updatedGameOrder);
    }
}

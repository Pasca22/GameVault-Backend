package com.example.mppbackend.service;

import com.example.mppbackend.entity.GameOrder;
import com.example.mppbackend.repository.GameOrderRepository;
import com.example.mppbackend.repository.UserRepository;
import com.example.mppbackend.validation.GameOrderValidation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GameOrderService {

    private GameOrderRepository gameOrderRepository;
    private UserRepository userRepository;

    public List<GameOrder> getAllGameOrders() {
        return gameOrderRepository.findAll();
    }

    public GameOrder getGameOrderById(Long id) {
        if (gameOrderRepository.findById(id).isEmpty()) {
            throw new RuntimeException("Game order with id " + id + " not found");
        }
        return gameOrderRepository.findById(id).get();
    }

    public GameOrder addGameOrder(Long userId, GameOrder gameOrder) {
        if (userRepository.findById(userId).isEmpty()) {
            throw new RuntimeException("User with id " + userId + " not found");
        }
        GameOrderValidation.validate(gameOrder);

        gameOrder.setUser(userRepository.findById(userId).get());
        gameOrderRepository.save(gameOrder);
        return gameOrder;
    }

    public GameOrder updateGameOrder(Long id, GameOrder updatedGameOrder) {
        if (gameOrderRepository.findById(id).isEmpty()) {
            throw new RuntimeException("Game order with id " + id + " not found");
        }
        GameOrderValidation.validate(updatedGameOrder);

        GameOrder gameOrder = gameOrderRepository.findById(id).get();

        gameOrder.setName(updatedGameOrder.getName());
        gameOrder.setDescription(updatedGameOrder.getDescription());
        gameOrder.setPrice(updatedGameOrder.getPrice());

        return gameOrderRepository.save(gameOrder);
    }

    public void deleteGameOrder(Long id) {
        if (gameOrderRepository.findById(id).isEmpty()) {
            throw new RuntimeException("Game order with id " + id + " not found");
        }
        gameOrderRepository.deleteById(id);
    }
}

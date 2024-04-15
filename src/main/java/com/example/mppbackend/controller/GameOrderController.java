package com.example.mppbackend.controller;

import com.example.mppbackend.entity.GameOrder;
import com.example.mppbackend.service.GameOrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/game-orders")
public class GameOrderController {

    private GameOrderService gameOrderService;

    @GetMapping
    public ResponseEntity<List<GameOrder>> getAllGameOrders() {
        List<GameOrder> gameOrders = gameOrderService.getAllGameOrders();
        return ResponseEntity.ok(gameOrders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameOrder> getGameOrderById(@PathVariable Long id) {
        GameOrder gameOrder = gameOrderService.getGameOrderById(id);
        return ResponseEntity.ok(gameOrder);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<GameOrder> addGameOrder(@PathVariable Long userId, @RequestBody GameOrder gameOrder) {
        GameOrder addedGameOrder = gameOrderService.addGameOrder(userId, gameOrder);
        return new ResponseEntity<>(addedGameOrder, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GameOrder> updateGameOrder(@PathVariable Long id, @RequestBody GameOrder gameOrder) {
        GameOrder updatedGameOrder = gameOrderService.updateGameOrder(id, gameOrder);
        return ResponseEntity.ok(updatedGameOrder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGameOrder(@PathVariable Long id) {
        gameOrderService.deleteGameOrder(id);
        return ResponseEntity.ok("Game order with id " + id + " deleted successfully");
    }
}

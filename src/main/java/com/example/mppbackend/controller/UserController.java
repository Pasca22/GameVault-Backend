package com.example.mppbackend.controller;

import com.example.mppbackend.dto.UserDto;
import com.example.mppbackend.entity.GameOrder;
import com.example.mppbackend.entity.User;
import com.example.mppbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/data")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/game_orders/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<GameOrder> getGameOrders(@PathVariable Long id) {
        return userService.getGameOrders(id);
    }

    @GetMapping("/mod")
    @PreAuthorize("hasRole('MODERATOR')")
    public String moderatorAccess() {
        return "Moderator Board.";
    }

    @GetMapping("/admin/users")
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/admin/game_orders")
    @PreAuthorize("hasRole('ADMIN')")
    public List<GameOrder> getAllGameOrders() {
        return userService.getAllGameOrders();
    }

    @DeleteMapping("/admin/delete_user/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "User with id " + id + " deleted successfully";
    }

    @PutMapping("/admin/update_user/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public UserDto updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @PostMapping("/mod/add_game_order/{userId}")
    @PreAuthorize("hasRole('MODERATOR')")
    public GameOrder addGameOrder(@PathVariable Long userId, @RequestBody GameOrder gameOrder) {
        return userService.addGameOrder(userId, gameOrder);
    }

    @PostMapping("/admin/add_game_order/{username}")
    @PreAuthorize("hasRole('ADMIN')")
    public GameOrder addGameOrderByAdmin(@PathVariable String username, @RequestBody GameOrder gameOrder) {
        return userService.addGameOrderByAdmin(username, gameOrder);
    }

    @DeleteMapping("/delete_game_order/{id}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public String deleteGameOrder(@PathVariable Long id) {
        userService.deleteGameOrder(id);
        return "Game order with id " + id + " deleted successfully";
    }

    @PutMapping("/update_game_order/{id}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public GameOrder updateGameOrder(@PathVariable Long id, @RequestBody GameOrder gameOrder) {
        return userService.updateGameOrder(id, gameOrder);
    }
}
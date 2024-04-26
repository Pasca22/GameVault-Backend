package com.example.mppbackend;

import com.example.mppbackend.entity.User;
import com.example.mppbackend.repository.UserRepository;
import com.example.mppbackend.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddUser() {
        User user = new User();
        user.setUsername("username");
        user.setEmail("email@asd.com");
        user.setPassword("paASD233232");
        user.setIp("1.1.1.1");
        user.setAvatar("avatar");
        when(userRepository.save(any(User.class))).thenReturn(user);
        User result = userService.addUser(user);
        assertNotNull(result);
        verify(userRepository).save(user);
    }

    @Test
    void testGetUserById_Found() {
        Long userId = 1L;
        User user = new User();
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        User result = userService.getUserById(userId);
        assertEquals(user, result);
    }

    @Test
    void testGetUserById_NotFound() {
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> userService.getUserById(userId));
    }

    @Test
    void testGetAllUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        when(userRepository.findAll()).thenReturn(users);
        List<User> result = userService.getAllUsers();
        assertFalse(result.isEmpty());
        assertEquals(2, result.size());
    }

    @Test
    void testUpdateUser_Found() {
        Long userId = 1L;
        User updatedUser = new User();
        updatedUser.setUsername("username");
        updatedUser.setEmail("email@asd.com");
        updatedUser.setPassword("paASD233232");
        updatedUser.setIp("1.1.1.1");
        updatedUser.setAvatar("avatar");
        when(userRepository.findById(userId)).thenReturn(Optional.of(new User()));
        when(userRepository.save(any(User.class))).thenReturn(updatedUser);
        User result = userService.updateUser(userId, updatedUser);
        assertNotNull(result);
    }

    @Test
    void testUpdateUser_NotFound() {
        Long userId = 1L;
        User updatedUser = new User();
        when(userRepository.findById(userId)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> userService.updateUser(userId, updatedUser));
    }

    @Test
    void testDeleteUser_Found() {
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.of(new User()));
        doNothing().when(userRepository).deleteById(userId);
        userService.deleteUser(userId);
        verify(userRepository).deleteById(userId);
    }

    @Test
    void testDeleteUser_NotFound() {
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> userService.deleteUser(userId));
    }


}

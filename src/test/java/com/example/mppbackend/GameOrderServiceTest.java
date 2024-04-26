package com.example.mppbackend;

import com.example.mppbackend.entity.GameOrder;
import com.example.mppbackend.entity.TableEntity;
import com.example.mppbackend.entity.User;
import com.example.mppbackend.repository.GameOrderRepository;
import com.example.mppbackend.repository.UserRepository;
import com.example.mppbackend.service.GameOrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class GameOrderServiceTest {
    @Mock
    private GameOrderRepository gameOrderRepository;
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private GameOrderService gameOrderService;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddGameOrder_Success() {
        GameOrder gameOrder = new GameOrder();
        gameOrder.setName("name");
        gameOrder.setDescription("description");
        gameOrder.setPrice(10.0);
        Long userId = 1L;
        User user = new User();
        user.setId(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(gameOrderRepository.save(any(GameOrder.class))).thenReturn(gameOrder);

        GameOrder result = gameOrderService.addGameOrder(userId, gameOrder);
        assertNotNull(result);
        verify(gameOrderRepository).save(gameOrder);
    }

    @Test
    void testAddGameOrder_UserNotFound() {
        GameOrder gameOrder = new GameOrder();
        Long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> gameOrderService.addGameOrder(userId, gameOrder));
    }

    @Test
    void testUpdateGameOrder_Found() {
        Long id = 1L;
        GameOrder updatedGameOrder = new GameOrder();
        updatedGameOrder.setName("name");
        updatedGameOrder.setDescription("description");
        updatedGameOrder.setPrice(10.0);
        when(gameOrderRepository.findById(id)).thenReturn(Optional.of(new GameOrder()));
        when(gameOrderRepository.save(any(GameOrder.class))).thenReturn(updatedGameOrder);

        GameOrder result = gameOrderService.updateGameOrder(id, updatedGameOrder);
        assertNotNull(result);
    }

    @Test
    void testUpdateGameOrder_NotFound() {
        Long id = 1L;
        GameOrder updatedGameOrder = new GameOrder();
        when(gameOrderRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> gameOrderService.updateGameOrder(id, updatedGameOrder));
    }

    @Test
    void testDeleteGameOrder_Found() {
        Long id = 1L;
        when(gameOrderRepository.findById(id)).thenReturn(Optional.of(new GameOrder()));
        doNothing().when(gameOrderRepository).deleteById(id);
        gameOrderService.deleteGameOrder(id);
        verify(gameOrderRepository).deleteById(id);
    }

    @Test
    void testDeleteGameOrder_NotFound() {
        Long id = 1L;
        when(gameOrderRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> gameOrderService.deleteGameOrder(id));
    }

    @Test
    void testGetGameOrderById_Found() {
        Long id = 1L;
        GameOrder gameOrder = new GameOrder();
        when(gameOrderRepository.findById(id)).thenReturn(Optional.of(gameOrder));
        GameOrder result = gameOrderService.getGameOrderById(id);
        assertNotNull(result);
    }

    @Test
    void testGetGameOrderById_NotFound() {
        Long id = 1L;
        when(gameOrderRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> gameOrderService.getGameOrderById(id));
    }

    @Test
    void testGetAllGameOrders() {
        List<GameOrder> gameOrders = new ArrayList<>();
        gameOrders.add(new GameOrder());
        gameOrders.add(new GameOrder());
        when(gameOrderRepository.findAll()).thenReturn(gameOrders);
        List<GameOrder> result = gameOrderService.getAllGameOrders();
        assertFalse(result.isEmpty());
        assertEquals(2, result.size());
    }

    @Test
    void testGetAllGameOrdersForTable() {
        int currentPage = 1;
        List<GameOrder> gameOrders = new ArrayList<>();
        User user = new User();
        user.setId(1L);
        user.setUsername("username");
        for (Long i = 0L; i < 20; i++) {
            GameOrder gameOrder = new GameOrder();
            gameOrder.setId(i);
            gameOrder.setName("name" + i);
            gameOrder.setDescription("description" + i);
            gameOrder.setPrice(10.0);
            gameOrder.setUser(user);
            gameOrders.add(gameOrder);
        }
        when(gameOrderRepository.findAll()).thenReturn(gameOrders);

        List<TableEntity> result = gameOrderService.getAllGameOrdersForTable(currentPage);
        assertNotNull(result);
        assertEquals(10, result.size()); // Assuming the page size is 10 and enough entities are available
    }


}

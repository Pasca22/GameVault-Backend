//package com.example.mppbackend.service;
//
//import com.corundumstudio.socketio.Configuration;
//import com.corundumstudio.socketio.SocketIOServer;
//import com.example.mppbackend.dto.UserDto;
//import com.example.mppbackend.entity.User;
//import com.example.mppbackend.mapper.UserMapper;
//import com.github.javafaker.Faker;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.PostConstruct;
//import javax.annotation.PreDestroy;
//import java.util.concurrent.Executors;
//import java.util.concurrent.ScheduledExecutorService;
//import java.util.concurrent.TimeUnit;
//
//@Service
//public class SocketService {
//    private final UserService userService;
//    private SocketIOServer server;
//
//    public SocketService(UserService userService) {
//        this.userService = userService;
//    }
//
//    @PostConstruct
//    public void init() {
//        Configuration config = new Configuration();
//        config.setHostname("localhost");
//        config.setPort(9092);
//
//        server = new SocketIOServer(config);
//        server.start();
//
//        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
//        executorService.scheduleAtFixedRate(this::sendNewUser, 10, 15, TimeUnit.SECONDS);
//    }
//
//    @PreDestroy
//    public void onDestroy() {
//        server.stop();
//    }
//
//    private void sendNewUser() {
//        Faker faker = new Faker();
//        String username = faker.name().username();
//        String password = faker.internet().password(8, 15, true);
//        String emailAddress = faker.internet().emailAddress();
//        String avatar = faker.internet().avatar();
//        String ip = faker.internet().ipV4Address();
//
//        User newUser = new User(-1, username, password, emailAddress, avatar, ip);
//        UserDto newUserDto = userService.addUser(UserMapper.mapToUserDto(newUser));
//        newUser = UserMapper.mapToUser(newUserDto);
//        server.getBroadcastOperations().sendEvent("newUser", newUserDto);
//    }
//}

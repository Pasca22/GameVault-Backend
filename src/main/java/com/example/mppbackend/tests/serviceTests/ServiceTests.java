package com.example.mppbackend.tests.serviceTests;

import com.example.mppbackend.dto.UserDto;
import com.example.mppbackend.service.UserService;

import java.util.List;
import java.util.Objects;

public class ServiceTests {
    private static UserService userService;

    public static void testService() {

        testAddUser();
        testGetUserById();
        testGetAllUsers();
        testUpdateUser();
        testDeleteUser();

        System.out.println("Service tests passed!");
    }

    private static void testAddUser() {
        userService = new UserService();
        UserDto userDtoInvalidPassword = new UserDto(7, "John", "Doe", "asd", "asd", "asd");
        UserDto userDtoInvalidEmail = new UserDto(7, "John", "DoeADSae321", "asd", "asd", "asd");
        UserDto userDtoInvalidIp = new UserDto(7, "John", "DoeADSae321", "asd@as.com", "asd", "asd");
        UserDto userDtoValid = new UserDto(7, "John", "DoeADSae321", "asd@as.com", "avatar", "1.1.1.1");

        try {
            userService.addUser(userDtoInvalidPassword);
        } catch (Exception e) {
            assert e.getMessage().equals("Password should contain at least one digit and uppercase letter " +
                    "and lowercase letter and must be at least 8 characters long");
        }

        try {
            userService.addUser(userDtoInvalidEmail);
        } catch (Exception e) {
            assert e.getMessage().equals("Invalid email format");
        }

        try {
            userService.addUser(userDtoInvalidIp);
        } catch (Exception e) {
            assert e.getMessage().equals("Invalid IP address format");
        }

        userService.addUser(userDtoValid);
        List<UserDto> users = userService.getAllUsers();
        assert users.size() == 7;
    }

    private static void testGetUserById() {
        userService = new UserService();

        try {
            userService.getUserById(7);
        } catch (Exception e) {
            assert e.getMessage().equals("User with id " + 7 + " not found");
        }

        UserDto userDto = userService.getUserById(1);
        assert userDto.getId() == 1;
    }

    private static void testGetAllUsers() {
        userService = new UserService();
        List<UserDto> users = userService.getAllUsers();
        assert users.size() == 6;
    }

    private static void testUpdateUser() {
        userService = new UserService();
        UserDto userWithId1 = userService.getUserById(1);
        assert Objects.equals(userWithId1.getUsername(), "Rosetta.Mante47");

        UserDto newUserDtoInvalidPassword = new UserDto(7, "John", "Doe", "asd", "asd", "asd");
        UserDto newUserDtoInvalidEmail = new UserDto(7, "John", "DoeADSae321", "asd", "asd", "asd");
        UserDto newUserDtoInvalidIp = new UserDto(7, "John", "DoeADSae321", "asd@as.com", "asd", "asd");
        UserDto newUserDtoValid = new UserDto(7, "John", "DoeADSae321", "asd@as.com", "avatar", "1.1.1.1");

        try {
            userService.updateUser(7, newUserDtoValid);
        } catch (Exception e) {
            assert e.getMessage().equals("User with id " + 7 + " not found");
        }

        try {
            userService.updateUser(1, newUserDtoInvalidPassword);
        } catch (Exception e) {
            assert e.getMessage().equals("Password should contain at least one digit and uppercase letter " +
                    "and lowercase letter and must be at least 8 characters long");
        }

        try {
            userService.updateUser(1, newUserDtoInvalidEmail);
        } catch (Exception e) {
            assert e.getMessage().equals("Invalid email format");
        }

        try {
            userService.updateUser(1, newUserDtoInvalidIp);
        } catch (Exception e) {
            assert e.getMessage().equals("Invalid IP address format");
        }

        UserDto updatedUser = userService.updateUser(1, newUserDtoValid);
        assert Objects.equals(updatedUser.getUsername(), "John");
    }

    private static void testDeleteUser() {
        userService = new UserService();

        try {
            userService.deleteUser(7);
        } catch (Exception e) {
            assert e.getMessage().equals("User with id " + 7 + " not found");
        }

        userService.deleteUser(1);

        assert userService.getAllUsers().size() == 5;
    }

}

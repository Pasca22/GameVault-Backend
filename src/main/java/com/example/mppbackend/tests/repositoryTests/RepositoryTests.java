package com.example.mppbackend.tests.repositoryTests;

import com.example.mppbackend.entity.User;
import com.example.mppbackend.repository.UserRepository;

import java.util.Objects;

public class RepositoryTests {

    private static UserRepository userRepository;

    public static void testRepository() {

        getByIdTest();
        addTest();
        updateTest();
        deleteTest();
        existsByIdTest();
        countTest();
        getAllTest();

        System.out.println("Repository tests passed!");
    }

    private static void getByIdTest() {
        userRepository = new UserRepository();
        User user = userRepository.getById(1);
        assert Objects.equals(user.getUsername(), "Rosetta.Mante47");
    }

    private static void addTest() {
        userRepository = new UserRepository();
        userRepository.add(new User(7, "Alena52", "ZEwLrDpu723Wwv7", "ads@2.com", "avatar", "ip"));
        assert userRepository.existsById(7);
    }

    private static void updateTest() {
        userRepository = new UserRepository();
        userRepository.update(5, new User(5, "asd", "asd", "asd", "asd", "asd"));
        User user = userRepository.getById(5);
        assert Objects.equals(user.getUsername(), "asd");
        assert Objects.equals(user.getPassword(), "asd");
    }

    private static void deleteTest() {
        userRepository = new UserRepository();
        userRepository.delete(2);
        assert !userRepository.existsById(2);
    }

    private static void existsByIdTest() {
        userRepository = new UserRepository();
        assert userRepository.existsById(3);
    }

    private static void countTest() {
        userRepository = new UserRepository();
        assert userRepository.count() == 6;
    }

    private static void getAllTest() {
        userRepository = new UserRepository();
        assert userRepository.getAll().size() == 6;
    }
}

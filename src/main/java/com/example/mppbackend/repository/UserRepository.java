package com.example.mppbackend.repository;

import com.example.mppbackend.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import com.github.javafaker.Faker;


public class UserRepository implements Repository<User, Integer> {

    List<User> users = new ArrayList<>();

    public UserRepository() {
        Faker faker = new Faker();
        for (int i = 1; i < 7; i++) {
            String username = faker.name().username();
            String password = faker.internet().password();
            String emailAddress = faker.internet().emailAddress();
            String avatar = faker.internet().avatar();
            String ip = faker.internet().ipV4Address();

            User newUser = new User(i, username, password, emailAddress, avatar, ip);
            users.add(newUser);
        }
    }

    @Override
    public User getById(Integer id) {
        return users.stream().filter(u -> Objects.equals(u.getId(), id)).findFirst().get();
    }

    @Override
    public void add(User user) {
        users.add(user);
    }

    @Override
    public User update(Integer id, User updatedUser) {
        User currentUser = getById(id);

        currentUser.setUsername(updatedUser.getUsername());
        currentUser.setPassword(updatedUser.getPassword());
        currentUser.setEmail(updatedUser.getEmail());
        currentUser.setAvatar(updatedUser.getAvatar());
        currentUser.setIp(updatedUser.getIp());

        return currentUser;
    }

    @Override
    public void delete(Integer id) {
        users.removeIf(u -> Objects.equals(u.getId(), id));
    }

    @Override
    public boolean existsById(Integer id) {
        return users.stream().anyMatch(u -> u.getId() == id);
    }

    @Override
    public int count() {
        return users.size();
    }

    @Override
    public List<User> getAll() {
        return users;
    }

    @Override
    public Integer getNextId() {
        return users.stream().map(User::getId).max(Integer::compareTo).orElse(0) + 1;
    }
}

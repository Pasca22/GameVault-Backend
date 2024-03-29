package com.example.mppbackend.tests.entityTests;

import com.example.mppbackend.entity.User;

public class UserTests {
    private static final User user = new User();

    public static void testUser() {
        user.setId(1);
        user.setUsername("user");
        user.setPassword("Password1");
        user.setEmail("user@u.com");
        user.setAvatar("avatar");

        assert user.getId() == 1;
        assert user.getUsername().equals("user");
        assert user.getPassword().equals("Password1");
        assert user.getEmail().equals("user@u.com");
        assert user.getAvatar().equals("avatar");

        System.out.println("User tests passed!");
    }

}

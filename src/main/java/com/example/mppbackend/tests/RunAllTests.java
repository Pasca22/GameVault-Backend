package com.example.mppbackend.tests;

import com.example.mppbackend.tests.entityTests.UserTests;
import com.example.mppbackend.tests.repositoryTests.RepositoryTests;
import com.example.mppbackend.tests.serviceTests.ServiceTests;

public class RunAllTests {
    public static void main(String[] args) {
        System.out.println("Running all tests...\n");

        UserTests.testUser();
        RepositoryTests.testRepository();
        ServiceTests.testService();

        System.out.println("\nAll tests passed!");
    }
}

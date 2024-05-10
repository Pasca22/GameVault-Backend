//package com.example.mppbackend.validation;
//
//import com.example.mppbackend.entity.GameOrder;
//
//public class GameOrderValidation {
//
//    public static void validate(GameOrder gameOrder) {
//        if (gameOrder.getName().length() < 3) {
//            throw new RuntimeException("Game order name must be at least 3 characters long");
//        }
//        if (gameOrder.getDescription().length() < 3) {
//            throw new RuntimeException("Game order description must be at least 3 characters long");
//        }
//        if (gameOrder.getPrice() < 0) {
//            throw new RuntimeException("Game order price must be greater than or equal to 0");
//        }
//    }
//}

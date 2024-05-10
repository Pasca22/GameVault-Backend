//package com.example.mppbackend.validation;
//
//import com.example.mppbackend.entity.User;
//
//import java.util.regex.Pattern;
//
//public class UserValidation {
//
//    private static final String zeroTo255
//            = "(\\d{1,2}|(0|1)\\"
//            + "d{2}|2[0-4]\\d|25[0-5])";
//    private static final String ipRegex
//            = zeroTo255 + "\\."
//            + zeroTo255 + "\\."
//            + zeroTo255 + "\\."
//            + zeroTo255;
//    private static final Pattern ipPattern = Pattern.compile(ipRegex);
//    private static final String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$";
//    private static final Pattern emailPattern = Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
//            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
//
//    public static void validate(User user) {
//        if (user.getUsername().length() < 3) {
//            throw new RuntimeException("Username must be at least 3 characters long");
//        }
//        if (!user.getPassword().matches(passwordRegex)) {
//            throw new RuntimeException("Password should contain at least one digit and uppercase letter " +
//                    "and lowercase letter and must be at least 8 characters long");
//        }
//        if (!emailPattern.matcher(user.getEmail()).matches()) {
//            throw new RuntimeException("Invalid email format");
//        }
//        if (!ipPattern.matcher(user.getIp()).matches()) {
//            throw new RuntimeException("Invalid IP address format");
//        }
//    }
//}

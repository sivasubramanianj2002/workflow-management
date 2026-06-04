package com.workflow.util;

import com.workflow.exceptions.ValidationException;

import java.util.regex.Pattern;

public class Validation {

    public Validation(){

    }
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile(
                    "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
            );

    public static void validateName(String name) {

        if (name == null || name.trim().isEmpty()) {
            throw new ValidationException(
                    "Name cannot be empty"
            );
        }

        if (name.trim().length() < 3) {
            throw new ValidationException(
                    "Name must contain at least 3 characters"
            );
        }
    }
    public static void validateEmail(String email) {

        if (email == null || email.trim().isEmpty()) {
            throw new ValidationException(
                    "Email cannot be empty"
            );
        }

        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new ValidationException(
                    "Invalid email format"
            );
        }
    }
    public static void validatePassword(String password) {

        if (password == null || password.isBlank()) {
            throw new ValidationException(
                    "Password cannot be empty"
            );
        }

        if (password.length() < 8) {
            throw new ValidationException(
                    "Password must contain at least 8 characters"
            );
        }
    }

}

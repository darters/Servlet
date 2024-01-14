package com.example.demo15;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ErrorRegisterHandle {
    public static boolean errorRegisterHandle(String name, String email, String password, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean isValid = true;

        if (name == null || name.length() < 4) {
            // Display an error message for invalid name
            req.setAttribute("errorName", "Invalid name must contain more than 4 characters");
            isValid = false;
        } else {
            req.setAttribute("saved_name", name);
            req.setAttribute("errorName", "");
        }

        if (email == null || email.length() < 9 || !email.contains("@")) {
            // Display an error message for invalid email
            req.setAttribute("errorEmail", "Invalid email must be unique, contain @, and more than 9 characters");
            isValid = false;
        } else {
            req.setAttribute("saved_email", email);
            req.setAttribute("errorEmail", "");
        }

        if (password == null || password.length() < 5) {
            // Display an error message for invalid password
            req.setAttribute("errorPassword", "Invalid password must contain more than 5 characters");
            isValid = false;
        } else {
            req.setAttribute("errorPassword", "");
        }

        return isValid;
    }
}

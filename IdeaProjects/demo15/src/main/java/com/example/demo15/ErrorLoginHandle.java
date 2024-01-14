package com.example.demo15;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ErrorLoginHandle {
    public static boolean errorLoginHandle(String email, String password, String jspFile, String jspFileIfFailed, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean isValid = true;
        if (email == null || email.length() < 9 || !email.contains("@")) {
            // Display an error message for invalid email
            req.setAttribute("errorEmail", "Invalid email must contain @, and more than 9 characters");
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
        if(isValid){
            System.out.println("Valid");
        } else {
            System.out.println("NONE VALID");
        }
        return isValid;
    }
}

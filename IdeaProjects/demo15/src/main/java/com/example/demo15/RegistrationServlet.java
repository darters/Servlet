package com.example.demo15;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;


@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        Connection connection = null;
        boolean isValid = ErrorRegisterHandle.errorRegisterHandle(name, email, password, req, resp);
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/site", "root", "3452");
            String emailUniqueReqSql = "SELECT COUNT(*) FROM user WHERE email = ?";
            PreparedStatement emailCount = connection.prepareStatement(emailUniqueReqSql);
            emailCount.setString(1, email);
            ResultSet resultSet = emailCount.executeQuery();
            if(resultSet.next()) {
                int count = resultSet.getInt(1);
                boolean isEmailUnique;
                if (count > 0) {
                    req.setAttribute("emailUnique", "Email already use");
                    isEmailUnique = false;
                } else {
                    String insertSql = "insert into user(name, email, password) values (?,?,?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
                    preparedStatement.setString(1, name);
                    preparedStatement.setString(2, email);
                    preparedStatement.setString(3, password);
                    int rowCount = preparedStatement.executeUpdate();
                    preparedStatement.close();
                    if(rowCount == 0) {
                        req.setAttribute("status", "success");
                    } else {
                        req.setAttribute("status", "failed");
                    }
                    isEmailUnique = true;
                }

                if(isValid && isEmailUnique) {
                    RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
                    dispatcher.forward(req, resp);
                } else {
                    RequestDispatcher dispatcher = req.getRequestDispatcher("registration.jsp");
                    dispatcher.forward(req, resp);
                }
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
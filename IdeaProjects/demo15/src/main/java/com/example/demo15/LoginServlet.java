package com.example.demo15;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String inputPassword = req.getParameter("password");

        boolean isValidDate = ErrorLoginHandle.errorLoginHandle(email, inputPassword, "index.jsp", "login.jsp", req, resp);
        HttpSession httpSession = req.getSession();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/site", "root", "3452");
            PreparedStatement preparedStatement = connection.prepareStatement("select * from user where email = ? and password = ?");
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, inputPassword);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                String passwordFromDb = (resultSet.getString("password"));
                if(isValidDate && passwordFromDb.equals(inputPassword)) {
                    req.setAttribute("status", "success");
                    httpSession.setAttribute("name", resultSet.getString("name"));
                    resp.sendRedirect("index.jsp");
                    RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
                    dispatcher.forward(req, resp);
                } else {
                    RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
                    dispatcher.forward(req, resp);
                }
            } else {
                req.setAttribute("status", "failed");
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("login.jsp");
                requestDispatcher.forward(req, resp);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}

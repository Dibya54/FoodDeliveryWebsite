package com.foodApp.sevlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foodApp.DAOImpl.UserDAOImpl;
import com.foodApp.Secret.Encrypt;



@WebServlet("/Register")
public class Register extends HttpServlet {
    
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	
    	// Encrypt sensitive fields
    	String encryptedName = Encrypt.encrypt(req.getParameter("name"));
    	String encryptedEmail = Encrypt.encrypt(req.getParameter("email"));
    	String encryptedMobile = Encrypt.encrypt(req.getParameter("mobile"));
    	String encryptedPassword = Encrypt.encrypt(req.getParameter("password")); 	
    	


        resp.setContentType("text/html");

        // Initialize the DAO object
        UserDAOImpl userDAO = new UserDAOImpl();

        // Add customer and get the result of the registration process
        boolean result = userDAO.addUser(encryptedName, encryptedEmail, encryptedPassword, encryptedMobile);
        
        if (result) {
        	resp.sendRedirect("login.html");
        } else {
        	resp.sendRedirect("register.html");

        }
    }
}

package com.foodApp.sevlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.foodApp.DAOImpl.UserDAOImpl;
import com.foodApp.Model.User;
import com.foodApp.Secret.Decrypt;
import com.foodApp.Secret.Encrypt;



public class Login extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email"); 
		String password = req.getParameter("password");
		
		System.out.println(email);
		System.out.println(password);
		 // Encrypt the email before passing it to getUser
        String encryptedEmail = Encrypt.encrypt(email);
        
        // Log the encrypted email for debugging
        System.out.println("Encrypted Email: " + encryptedEmail);
        
		UserDAOImpl udaoi = new UserDAOImpl();
		User u = udaoi.getUser(encryptedEmail);
		
		if(u != null) {
			   // Decrypt the stored password
			String decryptedPassword = Decrypt.decrypt(u.getPassword());
			
			// Debug statements to check values
            System.out.println("Decrypted Password from DB: " + decryptedPassword);
            System.out.println("Input Password: " + password);
            
			if(decryptedPassword.equals(password)) {
				HttpSession session = req.getSession();
				 // Decrypt other fields before setting them in session if needed
		        u.setEmail(Decrypt.decrypt(u.getEmail()));
		        u.setMobile(Decrypt.decrypt(u.getMobile()));
				session.setAttribute("LoggedInUser", u);
				resp.sendRedirect("GetRestaurant");
				//success
			}else {
				resp.sendRedirect("pwdIncorrect.jsp");
				//wrong password
			}
			
		}else {
			System.out.println("User not found with encrypted email: " + encryptedEmail);
			resp.sendRedirect("invalidUser.jsp");
			//No such user
		}
	}

}

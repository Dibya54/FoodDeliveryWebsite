package com.foodApp.sevlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.foodApp.DAOImpl.OrderDaoImpl;
import com.foodApp.DAOImpl.OrderHistoryDaoImpl;
import com.foodApp.DAOImpl.RestaurantDAOImpl;
import com.foodApp.Model.Order;
import com.foodApp.Model.User;


@WebServlet("/OrderHistory")
public class OrderHistory extends HttpServlet {
		@Override
		protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			HttpSession session = req.getSession();

			// Retrieve userId from session
			User u = (User) session.getAttribute("LoggedInUser");
			//int userId = u.getUid() ;
			
	        // DAOs
//	        OrderHistoryDaoImpl orderHistoryDao = new OrderHistoryDaoImpl();
//	        OrderDaoImpl orderDao = new OrderDaoImpl();
//	        RestaurantDAOImpl restaurantDao = new RestaurantDAOImpl();
	        
	     // Fetch order history by userId
//	        List<OrderHistory> orderHistoryList = ;
//	        List<Order> userOrders = new ArrayList<>();
			
			 if (u != null) {
		            int userId = u.getUid();

		            // Initialize DAO
		            OrderHistoryDaoImpl orderHistoryDao = new OrderHistoryDaoImpl();

		            // Fetch order history by userId
		            List<com.foodApp.Model.OrderHistory> orderHistoryList =  orderHistoryDao.getOrderHistroyItem(userId);
		            
		            System.out.println("OrderHistory: "+ orderHistoryList);

		            // Store the orderHistoryList back into the session
		            session.setAttribute("OrderHistoryList", orderHistoryList);

		            // Optionally, redirect to a JSP page to display order history
		            req.getRequestDispatcher("orderHistoryList.jsp").forward(req, resp);
		        } else {
		            // Redirect to login page if user is not logged in
		            resp.sendRedirect("login.jsp");
		        }
            
	        
	        


		}
}

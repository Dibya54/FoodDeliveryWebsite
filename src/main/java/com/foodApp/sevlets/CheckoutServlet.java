package com.foodApp.sevlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.foodApp.DAOImpl.OrderItemDaoImpl;
import com.foodApp.Model.CartItem;
import com.foodApp.Model.OrderItem;
import com.foodApp.Model.User;

@WebServlet("/CheckoutServlet")
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Database credentials
	private static final String DB_URL = "jdbc:mysql://localhost:3306/foods";
	private static final String DB_USERNAME = "root";
	private static final String DB_PASSWORD = "root";

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		// Retrieve userId from session
		User u = (User) session.getAttribute("LoggedInUser");
		int userId = u.getUid() ;

		//@SuppressWarnings("unchecked")
		HashMap<Integer, CartItem> cart = (HashMap<Integer, CartItem>) session.getAttribute("cart");

		if (cart == null || cart.isEmpty()) {
			request.setAttribute("error", "Your cart is empty. Please add items to your cart.");
			request.getRequestDispatcher("CartItem.jsp").forward(request, response);
			return;
		}


		try {
			// Fetch restaurantId and payment mode from the request
			int restaurantId = (int)session.getAttribute("restaurantId");
			String modeOfPayment = request.getParameter("payment");

			// Instantiate OrderItemDaoImpl and fetch order items
			OrderItemDaoImpl orderItemDao = new OrderItemDaoImpl();

			double totalAmount = 0.0;
			for(CartItem cartItem : cart.values()){
				totalAmount += cartItem.getPrice() * cartItem.getQuantity();
			}



			// Database operations
			try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
				int orderId = -1;

				// Insert into 'order' table
				String insertOrderQuery = "INSERT INTO `order` (`restaurantId`, `userId`, `totalAmount`, `modeOfPayment`, `status`) VALUES (?, ?, ?, ?, ?)";
				try (PreparedStatement orderStmt = connection.prepareStatement(insertOrderQuery, Statement.RETURN_GENERATED_KEYS)) {
					orderStmt.setInt(1, restaurantId);
					orderStmt.setInt(2, userId);
					orderStmt.setDouble(3, totalAmount);
					orderStmt.setString(4, modeOfPayment);
					orderStmt.setString(5, "Placed");
					orderStmt.executeUpdate();

					// Retrieve generated orderId
					try (ResultSet generatedKeys = orderStmt.getGeneratedKeys()) {
						if (generatedKeys.next()) {
							orderId = generatedKeys.getInt(1);
						}
					}
				}

				// Insert each cart item into `orderitem` table
				String insertOrderItemQuery = "INSERT INTO `orderitem` (`userId`, `restaurantId`, `menuId`, `quantity`, `totalAmount`, `orderId`) VALUES (?, ?, ?, ?, ?, ?)";
				try (PreparedStatement orderItemStmt = connection.prepareStatement(insertOrderItemQuery)) {
					for (CartItem cartItem : cart.values()) {
						orderItemStmt.setInt(1, userId);
						orderItemStmt.setInt(2, restaurantId);
						orderItemStmt.setInt(3, cartItem.getItemId());
						orderItemStmt.setInt(4, cartItem.getQuantity());
						orderItemStmt.setDouble(5, cartItem.getPrice() * cartItem.getQuantity());
						orderItemStmt.setInt(6, orderId);
						orderItemStmt.addBatch();
					}
					orderItemStmt.executeBatch();
				}


				// Insert into 'orderhistory' table
				String insertHistoryQuery = "INSERT INTO `orderhistory` (`userId`, `orderId`) VALUES (?, ?)";
				try (PreparedStatement historyStmt = connection.prepareStatement(insertHistoryQuery)) {
					historyStmt.setInt(1, userId);
					historyStmt.setInt(2, orderId);
					historyStmt.executeUpdate();
				}

				

			
				
				// Pass cart details to the request for displaying in the JSP
				session.setAttribute("cart", cart);
				
				// For Debugging purpose that the cart values are being sent to the jsp page correctly
				for (CartItem item : cart.values()) {
				    System.out.println("MenuId: " + item.getItemId() + ", Quantity: " + item.getQuantity() + ", Price: " + item.getPrice());
				}


				// Forward order details to the confirmation JSP
				request.setAttribute("orderId", orderId);
				request.setAttribute("totalAmount", totalAmount);
				request.setAttribute("modeOfPayment", modeOfPayment);
				//request.setAttribute("orderItems", orderItems);

				RequestDispatcher dispatcher = request.getRequestDispatcher("orderConfirmation.jsp");
				dispatcher.forward(request, response);
			}
		} catch (Exception e) {
			throw new ServletException("Error during checkout", e);
		}
	}
}

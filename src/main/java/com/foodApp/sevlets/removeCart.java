package com.foodApp.sevlets;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.foodApp.Model.CartItem;


@WebServlet("/removeCart")
public class removeCart extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// Retrieve the cart from session
		HttpSession session = req.getSession();
		Map<Integer, CartItem> cart = (Map<Integer, CartItem>) session.getAttribute("cart");

		// Get the itemId parameter and validate it
		String itemIdParam = req.getParameter("itemId");
		if (cart != null && itemIdParam != null) {
			try {
				int itemId = Integer.parseInt(itemIdParam);
				// Remove the item from the cart
				if (cart.containsKey(itemId)) {
					cart.remove(itemId);
					session.setAttribute("cart", cart); // Update the session cart
				} else {
					System.out.println("Item with ID " + itemId + " not found in the cart.");
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid itemId: " + itemIdParam);
			}
		}
		else {
			System.out.println("Cart is null or itemId is missing.");
		}
		session.setAttribute("cart", cart);

		resp.sendRedirect("CartItem.jsp");

	

}
}



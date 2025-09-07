package com.foodApp.sevlets;
import java.io.IOException;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.foodApp.DAOImpl.MenuDaoImpl;
import com.foodApp.Model.CartItem;
import com.foodApp.Model.Menu;


@WebServlet("/AddToCartController")
public class AddToCartController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		@SuppressWarnings("unchecked")
		HashMap<Integer, CartItem> cart = (HashMap<Integer, CartItem>) req.getSession().getAttribute("cart");

		if (cart == null) {
		    cart = new HashMap<>();
		    req.getSession().setAttribute("cart",cart);
		}

		// Parse menuId and quantity with null checks
		String menuIdStr = req.getParameter("menuId");
		String quantityStr = req.getParameter("quantity");
		
		System.out.println("ATC 34 menu"+menuIdStr+" quantity : "+quantityStr);

		if (menuIdStr == null || quantityStr == null) {
		    req.setAttribute("error", "Invalid input. Please provide both menu ID and quantity.");
		    req.getRequestDispatcher("cartItem.jsp").forward(req, resp);
		    return;
		}

		int menuId = Integer.parseInt(menuIdStr);
		int quantity = Integer.parseInt(quantityStr);
		System.out.println("ATC 44 MenuID: " + menuId + "  Quantity: " + quantity);

		// Ensure quantity is valid
		if (quantity <= 0) {
		    req.setAttribute("error", "Quantity must be greater than 0.");
		    req.getRequestDispatcher("CartItem.jsp").forward(req, resp);
		    return;
		}

		// Fetch menu item details using MenuDAOImpl
		MenuDaoImpl menuDAO = new MenuDaoImpl();
		Menu menu = menuDAO.getMenu(menuId);
		System.out.println("ATC 56 "+menu);
		if (menu != null) {
		    CartItem cartItem = new CartItem(menuId, menu.getRestaurantId(), menu.getName(), quantity, menu.getPrice());
		    System.out.println("CartItem: " + cartItem);

		    // Check if item is already in cart; if so, update quantity
		    if (cart.containsKey(menuId)) {
		        CartItem existingItem = cart.get(menuId);
		        existingItem.setQuantity(existingItem.getQuantity() + quantity);
		    } else {
		        cart.put(menuId, cartItem); // Add new item
		    }

		    // Update the cart in session
		    req.getSession().setAttribute("cart", cart);

		    // Redirect to cart page to reflect changes
		    resp.sendRedirect("CartItem.jsp");
		} else { 
		    req.setAttribute("error", "Menu item not found.");
		    req.getRequestDispatcher("menu.jsp").forward(req, resp);
		}
	}
}

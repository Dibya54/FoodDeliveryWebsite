package com.foodApp.sevlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.foodApp.DAOImpl.RestaurantDAOImpl;
import com.foodApp.Model.Restaurant;

@WebServlet("/GetRestaurant")
public class GetRestaurant extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get the current session, don't create a new one if it doesn't exist
        HttpSession session = req.getSession(false);

        // Check if there is a logged-in user
        if (session != null && session.getAttribute("LoggedInUser") != null) { // Use "LoggedInUser"
            // Initialize RestaurantDAOImpl and fetch the list of restaurants
            RestaurantDAOImpl restaurantDAO = new RestaurantDAOImpl();
            List<Restaurant> restList = restaurantDAO.getRestaurants();

            // Add the restaurant list to the session
            session.setAttribute("restList", restList);

            // Forward to a JSP page (e.g., "homepage.jsp") to display the restaurants
            req.getRequestDispatcher("/home.jsp").forward(req, resp);
        } else {
            // If no logged-in user, redirect to the login page
            resp.sendRedirect("login.jsp");
        }
    }
}

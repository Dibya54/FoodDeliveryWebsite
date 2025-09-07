package com.foodApp.sevlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foodApp.DAOImpl.OrderItemDaoImpl;
import com.foodApp.Model.OrderItem;


@WebServlet("/ViewOrderDetailsController")
public class ViewOrderDetailsController extends HttpServlet {
	
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Step 1: Retrieve orderId from the request
        int orderId = Integer.parseInt(request.getParameter("orderId"));

        // Step 2: Fetch order details using DAO
        OrderItemDaoImpl orderItemDao = new OrderItemDaoImpl();
        List<OrderItem> orderItemsList = orderItemDao.getOrderDetailsByOrderId(orderId); // Get list of OrderItem

        // Step 3: Set data in the request scope and forward to JSP
        request.setAttribute("orderItemsList", orderItemsList); // Set the correct attribute name
        RequestDispatcher dispatcher = request.getRequestDispatcher("orderDetails.jsp");
        dispatcher.forward(request, response);
    }

}

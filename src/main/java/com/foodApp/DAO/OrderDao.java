package com.foodApp.DAO;

import java.util.List;

import com.foodApp.Model.Order;

public interface OrderDao {
	
	void addOrder(Order order);
	Order getOrder(int orderId);
	void updateOrder(Order order);
	void deleteOrder(int orderId);
	List<Order>getAll();
}

package com.foodApp.DAO;

import java.util.List;

import com.foodApp.Model.OrderItem;

public interface OrderItemDao {
	
	void addOrderItem(OrderItem orderItem);
	OrderItem getOrderItem(int orderItemId);
	void UpdateOrderItem(OrderItem orderItem);
	List<OrderItem> getAllOrderItemsByUserId(int userid);
	void deleteOrderItem(int orderItemId);
	List<OrderItem>getAll();
	// Add new method to fetch order details by orderId (including restaurant and dish names)
    List<OrderItem> getOrderDetailsByOrderId(int orderId);
}

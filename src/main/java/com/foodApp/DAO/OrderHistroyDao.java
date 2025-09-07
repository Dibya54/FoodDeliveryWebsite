package com.foodApp.DAO;

import java.util.List;

import com.foodApp.Model.OrderHistory;

public interface OrderHistroyDao {
	
	void addOrderHistoryItem(OrderHistory orderHistory);
	List<OrderHistory> getOrderHistroyItem(int orderHistoryId);
	void updateOrderHistroyItem(OrderHistory orderHistory);
	void deleteOrderHistroyItem(int orderHistoryId);
	List<OrderHistory>getAll();
}

package com.foodApp.Model;

public class OrderItem {
	 private int orderItemId;
	    private int userId;
	    private int restaurantId;
	    private int menuId;
	    private int quantity;
	    private double totalAmount;
	    private int orderId;
	    private String restaurantName;
	    private String dishName;

	    public OrderItem() {
	    }

	    public OrderItem(int orderItemId, int userId, int restaurantId, int menuId, int quantity, double totalAmount, int orderId) {
	        super();
	        this.orderItemId = orderItemId;
	        this.userId = userId;
	        this.restaurantId = restaurantId;
	        this.menuId = menuId;
	        this.quantity = quantity;
	        this.totalAmount = totalAmount;
	        this.orderId = orderId;	  
	        }

	    public int getOrderItemId() {
	        return orderItemId;
	    }

	    public void setOrderItemId(int orderItemId) {
	        this.orderItemId = orderItemId;
	    }

	    public int getUserId() {
	        return userId;
	    }

	    public void setUserId(int userId) {
	        this.userId = userId;
	    }

	    public int getRestaurantId() {
	        return restaurantId;
	    }

	    public void setRestaurantId(int restaurantId) {
	        this.restaurantId = restaurantId;
	    }

	    public int getMenuId() {
	        return menuId;
	    }

	    public void setMenuId(int menuId) {
	        this.menuId = menuId;
	    }

	    public int getQuantity() {
	        return quantity;
	    }

	    public void setQuantity(int quantity) {
	        this.quantity = quantity;
	    }

	    public double getTotalAmount() {
	        return totalAmount;
	    }

	    public void setTotalAmount(double totalAmount) {
	        this.totalAmount = totalAmount;
	    }
	    
	    public int getOrderId() {
	        return orderId;
	    }

	    public void setOrderId(int orderId) {
	        this.orderId = orderId;
	    }
	    

	    public String getRestaurantName() {
	        return restaurantName;
	    }

	    public void setRestaurantName(String restaurantName) {
	        this.restaurantName = restaurantName;
	    }

	    public String getDishName() {
	        return dishName;
	    }

	    public void setDishName(String dishName) {
	        this.dishName = dishName;
	    }

	    @Override
	    public String toString() {
	        return "OrderItem [orderItemId=" + orderItemId + ", userId=" + userId + ", restaurantId=" + restaurantId
	                + ", menuId=" + menuId + ", quantity=" + quantity + ", totalAmount=" + totalAmount + ", orderId=" + orderId
	                + ", restaurantName=" + restaurantName + ", dishName=" + dishName + "]";
	    }
	    
	    
}

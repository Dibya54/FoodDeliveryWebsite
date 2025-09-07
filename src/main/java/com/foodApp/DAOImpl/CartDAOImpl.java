package com.foodApp.DAOImpl;

import java.util.HashMap;
import java.util.Map;
import com.foodApp.Model.CartItem;

public class CartDAOImpl {
	private Map<Integer, CartItem> items;

    public CartDAOImpl() {
        this.items = new HashMap<>();
    }

    // Add an item to the cart
    public Map<Integer, CartItem> addItem(CartItem item) {
        int itemId = item.getItemId();

        if (items.containsKey(itemId)) {
            CartItem existingItem = items.get(itemId);
            existingItem.setQuantity(existingItem.getQuantity() + item.getQuantity());
        } else {
            items.put(itemId, item);
        }
        return items;
    }

    // Update the quantity of an item in the cart
    public void updateItem(int itemId, int newQuantity) {
        if (items.containsKey(itemId)) {
            CartItem item = items.get(itemId);
            item.setQuantity(newQuantity);
        } else {
            System.out.println("Item not found in the cart.");
        }
    }

    // Remove an item from the cart
    public void removeItem(int itemId) {
        if (items.containsKey(itemId)) {
            items.remove(itemId);
        } else {
            System.out.println("Item not found in the cart.");
        }
    }

    // Get a specific item by itemId
    public CartItem getItem(int itemId) {
        return items.get(itemId);
    }

    // Get all items in the cart
    public Map<Integer, CartItem> getAllItems() {
        return new HashMap<>(items); // Returns a copy of the cart items
    }

 // Clear all items from the cart
    public void clearCart() {
        items.clear();
    }
}

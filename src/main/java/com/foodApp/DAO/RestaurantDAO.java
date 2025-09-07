package com.foodApp.DAO;

import java.util.List;
import com.foodApp.Model.Restaurant;

public interface RestaurantDAO {
    List<Restaurant> getRestaurants(); // Added generic type

    Restaurant getRestaurant(int restaurantId);

    boolean updateRestaurant(Restaurant restaurant);

    boolean addRestaurant(String name, String imagepath, boolean isActive, float rating, String address);

    boolean removeRestaurant(int restaurantId);
}

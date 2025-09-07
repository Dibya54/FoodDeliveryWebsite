package com.foodApp.Model;

public class Restaurant {
    private int restaurantId;
    private String name;
    private String imagepath;
    private float rating;
    private String eta;
    private String cuisineType;
    private String address;
    private boolean isActive;

    // Default constructor
    public Restaurant() {
        super();
    }

    // Constructor with all parameters
    public Restaurant(int restaurantId, String name, String imagepath, float rating, String eta, 
                      String cuisineType, String address, boolean isActive) {
        super();
        this.restaurantId = restaurantId;
        this.name = name;
        this.imagepath = imagepath;
        this.rating = rating;
        this.eta = eta;
        this.cuisineType = cuisineType;
        this.address = address;
        this.isActive = isActive;
    }

    // Constructor with selected parameters
    public Restaurant(String name, String imagepath, float rating, String eta, 
                      String cuisineType, String address, boolean isActive) {
        super();
        this.name = name;
        this.imagepath = imagepath;
        this.rating = rating;
        this.eta = eta;
        this.cuisineType = cuisineType;
        this.address = address;
        this.isActive = isActive;
    }

    // Constructor with restaurantId only
    public Restaurant(int restaurantId) {
        super();
        this.restaurantId = restaurantId;
    }

    // Getters
    public int getRestaurantId() {
        return restaurantId;
    }

    public String getName() {
        return name;
    }

    public String getImagepath() {
        return imagepath;
    }

    public float getRating() {
        return rating;
    }

    public String getEta() {
        return eta;
    }

    public String getCuisineType() {
        return cuisineType;
    }

    public String getAddress() {
        return address;
    }

    public boolean getIsActive() {
        return isActive;
    }

    // Setters
    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setEta(String eta) {
        this.eta = eta;
    }

    public void setCuisineType(String cuisineType) {
        this.cuisineType = cuisineType;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    // toString method
    @Override
    public String toString() {
        return restaurantId + " " + name + " " + imagepath + " " + rating + " " + eta + " " + cuisineType + " " +
               address + " " + isActive;
    }
}

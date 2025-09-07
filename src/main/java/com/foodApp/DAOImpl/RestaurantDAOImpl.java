package com.foodApp.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.foodApp.DAO.RestaurantDAO;
import com.foodApp.Model.Restaurant;
import com.foodApp.SQLConnector.ConnectionFactory;

public class RestaurantDAOImpl implements RestaurantDAO {
    private static final String SELECT_QUERY = "SELECT * FROM restaurant";
    private static final String INSERT_QUERY = "INSERT INTO restaurant (name, imagepath, rating, eta, cuisineType, address, isActive) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String DELETE_QUERY = "DELETE FROM restaurant WHERE restaurantId = ?";
    private static final String UPDATE_QUERY = "UPDATE restaurant SET name = ?, imagepath = ?, rating = ?, eta = ?, cuisineType = ?, address = ?, isActive = ? WHERE restaurantId = ?";
    
    private Connection con;
    private Statement stmt;
    private ResultSet res;
    private List<Restaurant> restaurantList;

    // Close resources after each query
    private void closeResources() {
        try {
            if (res != null) res.close();
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Restaurant> getRestaurants() {  // Can be used as getAllPost()
        restaurantList = new ArrayList<>();
        try {
            con = ConnectionFactory.connect();
            stmt = con.createStatement();
            res = stmt.executeQuery(SELECT_QUERY);

            while (res.next()) {
                Restaurant restaurant = new Restaurant( // Use extractRestFromRs here
                    res.getInt("restaurantId"),
                    res.getString("name"),
                    res.getString("imagepath"),
                    res.getFloat("rating"),
                    res.getString("eta"),
                    res.getString("cuisineType"),
                    res.getString("address"),
                    res.getBoolean("isActive")
                );
                restaurantList.add(restaurant);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return restaurantList;
    }

    @Override
    public Restaurant getRestaurant(int restaurantId) {
        Restaurant restaurant = null;
        try {
            con = ConnectionFactory.connect();
            String query = "SELECT * FROM restaurant WHERE restaurantId = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, restaurantId);
            res = pstmt.executeQuery();

            if (res.next()) {
                restaurant = new Restaurant(  // Use extractRestFromRs here
                    res.getInt("restaurantId"),
                    res.getString("name"),
                    res.getString("imagepath"),
                    res.getFloat("rating"),
                    res.getString("eta"),
                    res.getString("cuisineType"),
                    res.getString("address"),
                    res.getBoolean("isActive")
                );
            }
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return restaurant;
    }

    @Override
    public boolean updateRestaurant(Restaurant restaurant) {
        try {
            con = ConnectionFactory.connect();
            PreparedStatement pstmt = con.prepareStatement(UPDATE_QUERY);
            pstmt.setString(1, restaurant.getName());
            pstmt.setString(2, restaurant.getImagepath());
            pstmt.setFloat(3, restaurant.getRating());
            pstmt.setString(4, restaurant.getEta());
            pstmt.setString(5, restaurant.getCuisineType());
            pstmt.setString(6, restaurant.getAddress());
            pstmt.setBoolean(7, restaurant.getIsActive());
            pstmt.setInt(8, restaurant.getRestaurantId());

            int updatedRows = pstmt.executeUpdate();
            pstmt.close();
            return updatedRows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            closeResources();
        }
    }

    @Override
    public boolean addRestaurant(String name, String imagepath, boolean isActive, float rating, String address) {
        // SQL query to insert the restaurant into the database
        String INSERT_QUERY = "INSERT INTO restaurant (name, imagepath, rating, address, isActive) VALUES (?, ?, ?, ?, ?)";

        try {
            // Establish a connection to the database
            con = ConnectionFactory.connect();
            
            // Prepare the SQL statement
            PreparedStatement pstmt = con.prepareStatement(INSERT_QUERY);

            // Set parameters for the prepared statement
            pstmt.setString(1, name);
            pstmt.setString(2, imagepath);
            pstmt.setFloat(3, rating);
            pstmt.setString(4, address);
            pstmt.setBoolean(5, isActive);

            // Execute the query and check if any rows were inserted
            int insertedRows = pstmt.executeUpdate();
            
            // Close the PreparedStatement
            pstmt.close();
            
            // Return true if rows were inserted, false otherwise
            return insertedRows > 0;
        } catch (Exception e) {
            // Print stack trace in case of an exception
            e.printStackTrace();
            return false;
        } finally {
            // Close database resources
            closeResources();
        }
    }


    @Override
    public boolean removeRestaurant(int restaurantId) {
        try {
            con = ConnectionFactory.connect();
            PreparedStatement pstmt = con.prepareStatement(DELETE_QUERY);
            pstmt.setInt(1, restaurantId);

            int deletedRows = pstmt.executeUpdate();
            pstmt.close();
            return deletedRows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            closeResources();
        }
    }

	
}

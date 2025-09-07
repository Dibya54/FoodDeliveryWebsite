package com.foodApp.DAOImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.foodApp.DAO.OrderItemDao;
import com.foodApp.Model.OrderItem;

public class OrderItemDaoImpl implements OrderItemDao {

    // Database connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/foods";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";

    // SQL Queries
    private static final String INSERT_QUERY = "INSERT INTO `orderitem`(`userId`, `restaurantId`, `menuId`, `quantity`, `totalAmount`, `orderId`) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_QUERY = "SELECT * FROM `orderitem` WHERE `orderItemId` = ?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM `orderitem`";
    private static final String UPDATE_QUERY = "UPDATE `orderitem` SET `userId` = ?, `restaurantId` = ?, `menuId` = ?, `quantity` = ?, `totalAmount` = ? WHERE `orderItemId` = ?";
    private static final String DELETE_QUERY = "DELETE FROM `orderitem` WHERE `orderItemId` = ?";
    private static final String SELECT_BY_USERID_QUERY = "SELECT * FROM `orderitem` WHERE `userId` = ?";

    private Connection connection;

    // Constructor to initialize the database connection
    public OrderItemDaoImpl() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Failed to initialize database connection", e);
        }
    }

    @Override
    public void addOrderItem(OrderItem orderItem) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setInt(1, orderItem.getUserId());
            preparedStatement.setInt(2, orderItem.getRestaurantId());
            preparedStatement.setInt(3, orderItem.getMenuId());
            preparedStatement.setInt(4, orderItem.getQuantity());
            preparedStatement.setDouble(5, orderItem.getTotalAmount());
            preparedStatement.setInt(6, orderItem.getOrderId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to add order item", e);
        }
    }

    @Override
    public OrderItem getOrderItem(int orderItemId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY)) {
            preparedStatement.setInt(1, orderItemId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapToOrderItem(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch order item with ID: " + orderItemId, e);
        }
        return null;
    }

    @Override
    public List<OrderItem> getAll() {
        List<OrderItem> orderItemsList = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_QUERY)) {
            while (resultSet.next()) {
                orderItemsList.add(mapToOrderItem(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch all order items", e);
        }
        return orderItemsList;
    }

    @Override
    public void UpdateOrderItem(OrderItem orderItem) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            preparedStatement.setInt(1, orderItem.getUserId());
            preparedStatement.setInt(2, orderItem.getRestaurantId());
            preparedStatement.setInt(3, orderItem.getMenuId());
            preparedStatement.setInt(4, orderItem.getQuantity());
            preparedStatement.setDouble(5, orderItem.getTotalAmount());
            preparedStatement.setInt(6, orderItem.getOrderItemId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update order item with ID: " + orderItem.getOrderItemId(), e);
        }
    }

    @Override
    public void deleteOrderItem(int orderItemId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {
            preparedStatement.setInt(1, orderItemId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete order item with ID: " + orderItemId, e);
        }
    }

    @Override
    public List<OrderItem> getAllOrderItemsByUserId(int userId) {
        List<OrderItem> orderItemsList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_USERID_QUERY)) {
            preparedStatement.setInt(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    orderItemsList.add(mapToOrderItem(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch order items for user ID: " + userId, e);
        }
        return orderItemsList;
    }
    
    public List<OrderItem> getOrderDetailsByOrderId(int orderId) {
        List<OrderItem> orderItemsList = new ArrayList<>();
        String query = "SELECT orderItemId, userId, restaurantId, menuId, quantity, totalAmount FROM orderitem WHERE orderId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, orderId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setOrderItemId(resultSet.getInt("orderItemId"));
                    orderItem.setUserId(resultSet.getInt("userId"));
                    orderItem.setRestaurantId(resultSet.getInt("restaurantId"));
                    orderItem.setMenuId(resultSet.getInt("menuId"));
                    orderItem.setQuantity(resultSet.getInt("quantity"));
                    orderItem.setTotalAmount(resultSet.getDouble("totalAmount"));
                    orderItem.setOrderId(orderId);
                    
                    // Fetch and set restaurant and dish names
                    orderItem.setRestaurantName(getRestaurantNameById(orderItem.getRestaurantId()));
                    orderItem.setDishName(getMenuNameById(orderItem.getMenuId()));

                    orderItemsList.add(orderItem);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch order details for Order ID: " + orderId, e);
        }

        return orderItemsList;
    }

    
    public String getRestaurantNameById(int restaurantId) {
        String query = "SELECT name FROM restaurant WHERE restaurantId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, restaurantId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("name");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch restaurant name for ID: " + restaurantId, e);
        }
        return null; // If no matching restaurant is found
    }
    
    public String getMenuNameById(int menuId) {
        String query = "SELECT name FROM menu WHERE menuId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, menuId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("name");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch menu name for ID: " + menuId, e);
        }
        return null; // If no matching menu is found
    }




    // Utility method to map ResultSet to OrderItem object
    private OrderItem mapToOrderItem(ResultSet resultSet) throws SQLException {
        int orderItemId = resultSet.getInt("orderItemId");
        int userId = resultSet.getInt("userId");
        int restaurantId = resultSet.getInt("restaurantId");
        int menuId = resultSet.getInt("menuId");
        int quantity = resultSet.getInt("quantity");
        double totalAmount = resultSet.getDouble("totalAmount");
        int orderId = resultSet.getInt("orderId");
        return new OrderItem(orderItemId, userId, restaurantId, menuId, quantity, totalAmount, orderId);
    }
}

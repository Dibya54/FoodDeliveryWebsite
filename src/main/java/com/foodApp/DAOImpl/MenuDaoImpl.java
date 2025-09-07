package com.foodApp.DAOImpl;

import com.foodApp.DAO.MenuDAO;
import com.foodApp.Model.Menu;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuDaoImpl implements MenuDAO {

    static final String INSERT_QUERY = "INSERT INTO `menu` (`menuId`, `name`, `price`, `description`, `imagePath`, `isAvailable`, `restaurantId`, `rating`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    static final String DELETE_QUERY = "DELETE FROM `menu` WHERE `menuId` = ?";
    static final String SELECT_QUERY = "SELECT * FROM `menu` WHERE `menuId` = ?";
    static final String UPDATE_QUERY = "UPDATE `menu` SET `name` = ?, `price` = ?, `description` = ?, `imagePath` = ?, `isAvailable` = ?, `restaurantId` = ?, `rating` = ? WHERE `menuId` = ?";
    static final String SELECTALL_QUERY = "SELECT * FROM `menu`";
    static final String SELECTBYRESTRO_QUERY = "SELECT * FROM `menu` WHERE `restaurantId` = ?";

    static Connection connection = null;

    public MenuDaoImpl() {
        String url = "jdbc:mysql://localhost:3306/foods";
        String username = "root";
        String password = "root";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addMenu(Menu menu) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setInt(1, menu.getMenuId());
            preparedStatement.setString(2, menu.getName());
            preparedStatement.setFloat(3, menu.getPrice());
            preparedStatement.setString(4, menu.getDescription());
            preparedStatement.setString(5, menu.getImagePath());
            preparedStatement.setBoolean(6, menu.isAvailable());
            preparedStatement.setInt(7, menu.getRestaurantId());
            preparedStatement.setFloat(8, menu.getRating());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteMenu(int menuId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {
            preparedStatement.setInt(1, menuId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Menu getMenu(int menuId) {
        Menu menu = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY)) {
            preparedStatement.setInt(1, menuId);
            try (ResultSet res = preparedStatement.executeQuery()) {
                if (res.next()) {
                    menu = new Menu(
                            menuId,
                            res.getString("name"),
                            res.getFloat("price"),
                            res.getString("description"),
                            res.getString("imagePath"),
                            res.getBoolean("isAvailable"),
                            res.getInt("restaurantId"),
                            res.getFloat("rating")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menu;
    }

    @Override
    public void updateMenu(Menu menu) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            preparedStatement.setString(1, menu.getName());
            preparedStatement.setFloat(2, menu.getPrice());
            preparedStatement.setString(3, menu.getDescription());
            preparedStatement.setString(4, menu.getImagePath());
            preparedStatement.setBoolean(5, menu.isAvailable());
            preparedStatement.setInt(6, menu.getRestaurantId());
            preparedStatement.setFloat(7, menu.getRating());
            preparedStatement.setInt(8, menu.getMenuId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Menu> getAll() {
        List<Menu> menuList = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet res = statement.executeQuery(SELECTALL_QUERY)) {
            while (res.next()) {
                Menu menu = new Menu(
                        res.getInt("menuId"),
                        res.getString("name"),
                        res.getFloat("price"),
                        res.getString("description"),
                        res.getString("imagePath"),
                        res.getBoolean("isAvailable"),
                        res.getInt("restaurantId"),
                        res.getFloat("rating")
                );
                menuList.add(menu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menuList;
    }

    @Override
    public List<Menu> getMenuByResto(int restaurantId) {
        List<Menu> menuList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECTBYRESTRO_QUERY)) {
            preparedStatement.setInt(1, restaurantId);
            try (ResultSet res = preparedStatement.executeQuery()) {
                while (res.next()) {
                    Menu menu = new Menu(
                            res.getInt("menuId"),
                            res.getString("name"),
                            res.getFloat("price"),
                            res.getString("description"),
                            res.getString("imagePath"),
                            res.getBoolean("isAvailable"),
                            res.getInt("restaurantId"),
                            res.getFloat("rating")
                    );
                    menuList.add(menu);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menuList;
    }
}

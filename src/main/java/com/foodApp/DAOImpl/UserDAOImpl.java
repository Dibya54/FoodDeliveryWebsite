package com.foodApp.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.foodApp.DAO.UserDAO;
import com.foodApp.Model.User;
import com.foodApp.SQLConnector.ConnectionFactory;
import com.foodApp.Secret.Decrypt;
import com.foodApp.Secret.Encrypt;

public class UserDAOImpl implements UserDAO {
    private static final String SELECT_QUERY = "SELECT * FROM user";
    private static final String SELECT_QUERY_WITH_EMAIL = "SELECT * FROM user WHERE email=?";
    private static final String INSERT_QUERY = "INSERT INTO user (username, email, password, mobile) VALUES (?, ?, ?, ?)";
    private static Connection con;
    private Statement stmt;
    private ResultSet res;
    private int status;
    private PreparedStatement pstmt;
    private ArrayList<User> userList;

    @Override
    public List<User> getUsers() {
        userList = new ArrayList<>();
        try {
            con = ConnectionFactory.connect();
            stmt = con.createStatement();
            res = stmt.executeQuery(SELECT_QUERY);
            while (res.next()) {
                User user = new User(
                    res.getInt("uid"),
                    res.getString("username"),
                    res.getString("email"),
                    res.getString("mobile"),
                    res.getString("password")
                );
                userList.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return userList;
    }

    @Override
    public User getUser(String email) {
        User user = null;
        try {
            con = ConnectionFactory.connect();
            pstmt = con.prepareStatement(SELECT_QUERY_WITH_EMAIL);
            pstmt.setString(1, email); // Use encrypted email in query
            res = pstmt.executeQuery();
            if (res.next()) {
                user = new User(
                    res.getInt("uid"),  // Keep uid as is
                    Decrypt.decrypt(res.getString("username")),
                    Decrypt.decrypt(res.getString("email")),   // Decrypt email
                    Decrypt.decrypt(res.getString("mobile")),  // Decrypt mobile
                    res.getString("password") // Keep password encrypted for later decryption
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return user;
    }

    @Override
    public boolean addUser(String username, String email, String password, String mobile) {
        boolean result = false;
        try {
            con = ConnectionFactory.connect();
            pstmt = con.prepareStatement(INSERT_QUERY);
            pstmt.setString(1, username);
            pstmt.setString(2, email);
            pstmt.setString(3, password);
            pstmt.setString(4, mobile);
            status = pstmt.executeUpdate();
            result = status > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return result;
    }

    @Override
    public boolean updateUser(User user) {
        // TODO: Implement update query
        return false;
    }

    @Override
    public boolean removeUser(String email) {
        // TODO: Implement delete query
        return false;
    }

    // Helper method to close resources
    private void closeResources() {
        try {
            if (res != null) res.close();
            if (stmt != null) stmt.close();
            if (pstmt != null) pstmt.close();
            if (con != null) con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

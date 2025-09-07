package com.foodApp.SQLConnector;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
    private static final String URL = "jdbc:mysql://localhost:3306/foods";
    private static final String USER = "root";
    private static final String PWD = "root";
    static Connection con;
    
    public static Connection connect() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(URL, USER, PWD);
        return con;
    }
}

package com.day_28.station.util;

import java.sql.*;

public class JDBCUtil {
    private static String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/station1";
    private static String username = "root";
    private static String password = "admin";
    private static Connection connection = null;

    static {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static Connection getConnection() {

        return connection;
    }
}

package com.ayman.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectivity {
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 3307 ;
    private static final String DB_NAME = "jdbc_course_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "aqzsedrf123";


    private static Connection connection ;
    public static Connection getConnection(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_course_db",USERNAME,PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}

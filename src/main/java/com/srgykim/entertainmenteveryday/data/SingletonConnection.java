package com.srgykim.entertainmenteveryday.data;

import java.sql.*;

/**
 * SingletonConnection class ensures that there is only one
 * object to establish the connection with the database
 */
public class SingletonConnection {

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/entertainmenteveryday";
    private static final String USER = "root";
    private static final String PASS = "";

    private static SingletonConnection instance = null;
    private static Connection connection = null;

    private SingletonConnection() {

        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private static SingletonConnection getInstance() {

        if (instance == null) {
            instance = new SingletonConnection();
        }

        return instance;
    }

    public static Connection getConnection() {

        if (connection == null) {
            getInstance();
        }

        return connection;
    }
}

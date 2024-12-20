package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        final String DATABASE_NAME = "jackie";
        final String URL = "jdbc:mysql://localhost:3306/" + DATABASE_NAME;
        final String USERNAME = "root";
        final String PASSWORD = "";

        try {
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connected to database successfully : " + conn);
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}
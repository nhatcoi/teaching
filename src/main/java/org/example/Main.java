package org.example;

import java.sql.*;

public class Main {
    public static void main(String[] args) {

        try {
            // ket noi voi db
            Connection conn = Database.getConnection();
            System.out.println("Connected to database successfully : " + conn);

            // select pet
            String sql = "SELECT * FROM pets";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Timestamp date = resultSet.getTimestamp("dob");

                System.out.println(
                        "ID: " + id + ", Name: " + name + ", Date: " + date + "\n"
                );
            }

            // insert - pets


            // click 1 - add - Bird
//            String sqlInsertData = "INSERT INTO pets (name, dob) VALUES (?, ?)";
//            preparedStatement = conn.prepareStatement(sqlInsertData);
//            preparedStatement.setString(1, "Bird");
//            preparedStatement.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
//            int rowInserted = preparedStatement.executeUpdate();
//            System.out.println("Row inserted: " + rowInserted);

            // update
            String sqlUpdateData = "UPDATE pets SET name = ? WHERE id = ?";
            preparedStatement = conn.prepareStatement(sqlUpdateData);
            preparedStatement.setString(1, "Shiba Dog");
            preparedStatement.setInt(2, 1);
            preparedStatement.executeUpdate();

            // delete
            String sqlDeleteData = "DELETE FROM pets WHERE id = ?";
            preparedStatement = conn.prepareStatement(sqlDeleteData);
            preparedStatement.setInt(1, 3);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
package org.example.service;

import org.example.DBConnection;
import org.example.model.Student;

import java.sql.*;


public class StudentServiceImpl implements StudentService {

    private final Connection connection;

    // Dependency Injection - Tiêm hàm getConnection() ở class DBConnection vào constructor này
    public StudentServiceImpl() throws SQLException {
        this.connection = DBConnection.getConnection();
    }


    // Service - CRUD

    @Override
    public void insertStudent(Student student) {
        String sql = "INSERT INTO students (name, email, dob) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, student.getName());
            statement.setString(2, student.getEmail());
            statement.setDate(3, Date.valueOf(student.getDob()));
            statement.executeUpdate();
            System.out.println("Thêm học viên thành công!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}

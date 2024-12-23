package org.example.service;

import org.example.DBConnection;
import org.example.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


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

    @Override
    public List<Student> getAllStudents() {
        String sql = "SELECT * FROM students";
        List<Student> students = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                students.add(mapRowToStudent(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public List<Student> getStudentsByName(String keyword) {
        String sql = "SELECT * FROM students WHERE name LIKE ?";
        List<Student> students = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "%" + keyword + "%");
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    students.add(mapRowToStudent(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public List<Student> getStudentsSortedByDOB() {
        String sql = "SELECT * FROM students ORDER BY dob ASC";
        List<Student> students = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                students.add(mapRowToStudent(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    private Student mapRowToStudent(ResultSet resultSet) throws SQLException {
        return new Student(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("email"),
                resultSet.getDate("dob").toLocalDate()
        );
    }


}

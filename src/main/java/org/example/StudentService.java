package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentService {


    // inject DB
    private final Connection connection;

    public StudentService() throws SQLException {
        connection = Database.getConnection();
    }

    public void addStudent(Student student) throws SQLException {

        String query = "INSERT INTO students (name, email, dob) VALUES(?,?,?)";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, student.getName());
        ps.setString(2, student.getEmail());
        ps.setDate(3,  student.getDob());
        ps.executeUpdate();

        System.out.println("Insert successful!");
    }


    public List<Student> getAllStudents() throws SQLException {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM students";
        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Student student = new Student();
            student.setId(rs.getInt("id"));
            student.setName(rs.getString("name"));
            student.setEmail(rs.getString("email"));
            student.setDob(Date.valueOf(rs.getDate("dob").toLocalDate()));
            students.add(student);
        }
        return students;
    }

    public Student getStudentById(int idStudent) throws SQLException {
        String query = "SELECT * FROM students WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, idStudent);
        ResultSet rs = ps.executeQuery();
        Student student = new Student();
        while (rs.next()) {
            student.setId(rs.getInt("id"));
            student.setName(rs.getString("name"));
            student.setEmail(rs.getString("email"));
            student.setDob(Date.valueOf(rs.getDate("dob").toLocalDate()));
        }
        return student;
    }


    public void updateStudent(int id, String name, String email, Date dob) throws SQLException {
        String query = "UPDATE students SET name = ?, email = ?, dob = ? WHERE id = ?";

        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, name);
        ps.setString(2, email);
        ps.setDate(2, dob);
        ps.setInt(3, id);
        ps.executeUpdate();
    }

    public void deleteStudent(int id) throws SQLException {
        String query = "DELETE FROM students WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
        System.out.println("delete successful!");
    }

}

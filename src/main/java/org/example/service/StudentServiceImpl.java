package org.example.service;

import org.example.DBConnection;
import org.example.model.Student;

import java.sql.*;
import java.time.LocalDate;
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



    // update

    @Override
    public void updateStudentEmail(int id, String newEmail) {
        String sql = "UPDATE students SET email = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, newEmail);
            statement.setInt(2, id);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Cập nhật email thành công!");
            } else {
                System.out.println("Không tìm thấy học viên với ID đã nhập.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteStudent(int id) {
        String sql = "DELETE FROM students WHERE id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Xóa học viên thành công!");
            } else {
                System.out.println("Không tìm thấy học viên với ID đã nhập.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }





    @Override
    public List<Student> getStudentsByPagination(int page, int pageSize) throws SQLException {
        List<Student> students = new ArrayList<>();
        int offset = (page - 1) * pageSize;

        String query = "SELECT * FROM students LIMIT ? OFFSET ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, pageSize);
            stmt.setInt(2, offset);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                LocalDate dob = rs.getDate("dob").toLocalDate();
                students.add(new Student(id, name, email, dob));
            }
        }
        return students;
    }

    @Override
    public int getTotalPages(int pageSize) throws SQLException {
        String countQuery = "SELECT COUNT(*) FROM students";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(countQuery);
            if (rs.next()) {
                int totalStudents = rs.getInt(1);
                return (int) Math.ceil((double) totalStudents / pageSize);
            }
        }
        return 0;
    }


    @Override
    public int getStudentCount() throws SQLException {
        String query = "SELECT COUNT(*) FROM students";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }

    @Override
    public Student getStudentWithMinDOB() throws SQLException {
        String query = "SELECT * FROM students ORDER BY dob ASC LIMIT 1";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                return new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getDate("dob").toLocalDate()
                );
            }
        }
        return null;
    }

    @Override
    public Student getStudentWithMaxDOB() throws SQLException {
        String query = "SELECT * FROM students ORDER BY dob DESC LIMIT 1";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                return new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getDate("dob").toLocalDate()
                );
            }
        }
        return null;
    }

    @Override
    public List<Student> searchStudents(String name, String email) throws SQLException {
        List<Student> students = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM students WHERE 1=1");

        // Nếu tên được nhập, thêm điều kiện tìm kiếm tên
        if (name != null && !name.isEmpty()) {
            query.append(" AND name LIKE ?");
        }

        // Nếu email được nhập, thêm điều kiện tìm kiếm email
        if (email != null && !email.isEmpty()) {
            query.append(" AND email LIKE ?");
        }

        try (PreparedStatement stmt = connection.prepareStatement(query.toString())) {

            int index = 1;
            // Nếu có tên, set parameter cho tên
            if (name != null && !name.isEmpty()) {
                stmt.setString(index++, "%" + name + "%");
            }

            // Nếu có email, set parameter cho email
            if (email != null && !email.isEmpty()) {
                stmt.setString(index, "%" + email + "%");
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Student student = new Student(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getDate("dob").toLocalDate()
                    );
                    students.add(student);
                }
            }
        }
        return students;
    }

}

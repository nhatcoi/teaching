package org.example.service;
import org.example.DBConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RentBookServiceImpl implements RentBookService {

    private final Connection connection;

    // Dependency Injection - Tiêm hàm getConnection() ở class DBConnection vào constructor này
    public RentBookServiceImpl() throws SQLException {
        this.connection = DBConnection.getConnection();
    }

    // Thêm bản ghi vào bảng rents
    @Override
    public void rentBook(int studentId, int bookId, LocalDate rentDate) throws SQLException {
        String query = "INSERT INTO rents (student_id, book_id, rent_date) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, studentId);
            stmt.setInt(2, bookId);
            stmt.setDate(3, Date.valueOf(rentDate));
            stmt.executeUpdate();
        }
    }

    // Hiển thị danh sách học viên và các sách đã mượn
    @Override
    public List<String> getRentedBooksByStudent(int studentId) throws SQLException {
        List<String> rentedBooks = new ArrayList<>();
        String query = "SELECT b.title, r.rent_date FROM rents r " +
                "JOIN books b ON r.book_id = b.id WHERE r.student_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                rentedBooks.add("Sách: " + rs.getString("title") + ", Ngày mượn: " + rs.getDate("rent_date"));
            }
        }
        return rentedBooks;
    }

    // Cập nhật thông tin đơn mượn sách
    @Override
    public void updateRentDate(int rentId, LocalDate newRentDate) throws SQLException {
        String query = "UPDATE rents SET rent_date = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setDate(1, Date.valueOf(newRentDate));
            stmt.setInt(2, rentId);
            stmt.executeUpdate();
        }
    }
}

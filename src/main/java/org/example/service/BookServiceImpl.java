package org.example.service;

import org.example.DBConnection;
import org.example.model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookServiceImpl implements BookService {

    private final Connection connection;

    // Dependency Injection - Tiêm hàm getConnection() ở class DBConnection vào constructor này
    public BookServiceImpl() throws SQLException {
        this.connection = DBConnection.getConnection();
    }

    @Override
    public void addBook(Book book) {
        String sql = "INSERT INTO books (title, author, published_date) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setDate(3, Date.valueOf(book.getPublishedDate()));
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Book> getAllBooksSorted() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books ORDER BY published_date DESC";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Book book = new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getDate("published_date").toLocalDate()
                );
                books.add(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public void updateBookTitle(int id, String newTitle) {
        String sql = "UPDATE books SET title = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, newTitle);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            System.out.println("Cập nhật tên sách thành công!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteBooksContaining(String keyword) {
        String sql = "DELETE FROM books WHERE title LIKE ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, "%" + keyword + "%");
            stmt.executeUpdate();
            System.out.println("Xóa sách thành công!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

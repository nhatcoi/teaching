package org.example;

import org.example.service.*;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        // Inject StudentService và BookService vào Main
        StudentService studentService = new StudentServiceImpl();
        BookService bookService = new BookServiceImpl();
        RentBookService rentBookService = new RentBookServiceImpl();

        // Tạo đối tượng View và gọi phương thức hiển thị menu chính
        View view = new View(studentService, bookService, rentBookService);
        view.showMainMenu();
    }
}
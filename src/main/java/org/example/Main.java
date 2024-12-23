package org.example;

import org.example.service.BookService;
import org.example.service.BookServiceImpl;
import org.example.service.StudentService;
import org.example.service.StudentServiceImpl;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        // Inject StudentService và BookService vào Main
        StudentService studentService = new StudentServiceImpl();
        BookService bookService = new BookServiceImpl();

        // Tạo đối tượng View và gọi phương thức hiển thị menu chính
        View view = new View(studentService, bookService);
        view.showMainMenu();
    }
}
package org.example;

import org.example.controller.BookController;
import org.example.controller.RentBookController;
import org.example.controller.StudentController;
import org.example.service.*;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        // Inject StudentService và BookService vào Main
        StudentController studentController = new StudentController(new StudentServiceImpl());
        BookController bookController = new BookController(new BookServiceImpl());
        RentBookController rentBookController = new RentBookController(new RentBookServiceImpl());

        // Tạo đối tượng View và gọi phương thức hiển thị menu chính
        View view = new View(studentController, bookController, rentBookController);
        view.showMainMenu();
    }
}
package org.example;

import org.example.model.Student;
import org.example.service.StudentService;
import org.example.service.StudentServiceImpl;

import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {

        // Inject StudentService vào Main
        StudentService studentService = new StudentServiceImpl();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== QUẢN LÝ HỌC VIÊN ===");
            System.out.println("1. Thêm học viên");
            System.out.println("2. Hiển thị danh sách học viên");
            System.out.println("3. Cập nhật email học viên");
            System.out.println("4. Xóa học viên");
            System.out.println("5. Thoát");
            System.out.print("Chọn chức năng: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    scanner.nextLine(); // Consume newline
                    System.out.print("Nhập tên: ");
                    String name = scanner.nextLine();
                    System.out.print("Nhập email: ");
                    String email = scanner.nextLine();
                    System.out.print("Nhập ngày sinh (yyyy-mm-dd): ");
                    LocalDate dob = LocalDate.parse(scanner.nextLine());
                    Student student = new Student(name, email, dob);
                    studentService.insertStudent(student);
                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:
                    System.out.println("Thoát chương trình.");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }

    }
}
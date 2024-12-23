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
            System.out.println("2. Hiển toàn bộ thị danh sách học viên");
            System.out.println("3. Tìm học viên theo tên");
            System.out.println("4. Danh sách học viên theo thứ tự tăng dần");
            System.out.println("5. Cập nhật email học viên");
            System.out.println("6. Xóa học viên");
            System.out.println("7. Thoát");
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
                    System.out.println("=== Toàn bộ danh sách học viên ===");
                    studentService.getAllStudents().forEach(System.out::println);

                    break;
                case 3:
                    scanner.nextLine();
                    System.out.println("Nhập tên cần tìm: ");
                    String keyword = scanner.nextLine();
                    System.out.println("\n=== Học viên có tên chứa '" + keyword + "' ===");
                    studentService.getStudentsByName(keyword).forEach(System.out::println);
                    break;
                case 4:
                    System.out.println("\n=== Danh sách học viên theo ngày sinh tăng dần ===");
                    studentService.getStudentsSortedByDOB().forEach(System.out::println);
                    break;
                case 5:
                    System.out.print("Nhập ID học viên: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Nhập email mới: ");
                    String newEmail = scanner.nextLine();
                    studentService.updateStudentEmail(id, newEmail);
                    break;
                case 6:
                    System.out.print("Nhập ID học viên cần xóa: ");
                    int deleteId = scanner.nextInt();
                    System.out.println("Bạn có muốn xoá học viên có ID = " + deleteId + " không? (Y/N)");
                    scanner.nextLine(); // Consume newline
                    String confirm = scanner.nextLine();
                    if (confirm.equalsIgnoreCase("Y"))
                        studentService.deleteStudent(deleteId);
                    else
                        System.out.println("Không xoá học viên nào.");
                    break;
                case 7:
                    System.out.println("Thoát chương trình.");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }
}
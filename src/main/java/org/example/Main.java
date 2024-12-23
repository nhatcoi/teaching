package org.example;

import org.example.model.Book;
import org.example.model.Student;
import org.example.service.BookService;
import org.example.service.BookServiceImpl;
import org.example.service.StudentService;
import org.example.service.StudentServiceImpl;

import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {

        // Inject StudentService vào Main
        StudentService studentService = new StudentServiceImpl();
        BookService bookService = new BookServiceImpl();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== QUẢN LÝ HỌC VIÊN ===");
            System.out.println("1. Thêm học viên");
            System.out.println("2. Hiển toàn bộ thị danh sách học viên");
            System.out.println("3. Tìm học viên theo tên");
            System.out.println("4. Danh sách học viên theo thứ tự tăng dần");
            System.out.println("5. Cập nhật email học viên");
            System.out.println("6. Xóa học viên");
            System.out.println("7. Quản lí sách");
            System.out.println("0. Thoát");
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
                    System.out.println("=== QUẢN LÝ SÁCH ===");
                    System.out.println("1. Thêm sách");
                    System.out.println("2. Hiển thị toàn bộ sách theo ngày");
                    System.out.println("3. Cập nhật tên sách");
                    System.out.println("4. Xóa sách theo tên");
                    System.out.print("Chọn chức năng: ");
                    int bookChoice = scanner.nextInt();
                    switch (bookChoice) {
                        case 1:
                            scanner.nextLine(); // Consume newline
                            System.out.print("Nhập tên sách: ");
                            String title = scanner.nextLine();
                            System.out.print("Nhập tên tác giả: ");
                            String author = scanner.nextLine();
                            System.out.print("Nhập ngày xuất bản (yyyy-mm-dd): ");
                            LocalDate publishedDate = LocalDate.parse(scanner.nextLine());
                            Book newBook = new Book(title, author, publishedDate);
                            bookService.addBook(newBook);
                            break;
                        case 2:
                            System.out.println("=== Toàn bộ danh sách sách ===");
                            bookService.getAllBooksSorted().forEach(System.out::println);
                            break;
                        case 3:
                            System.out.print("Nhập ID sách cần cập nhật: ");
                            int bookId = scanner.nextInt();
                            scanner.nextLine(); // Consume newline
                            System.out.print("Nhập tên mới: ");
                            String newTitle = scanner.nextLine();
                            bookService.updateBookTitle(bookId, newTitle);
                            break;
                        case 4:
                            scanner.nextLine(); // Consume newline
                            System.out.print("Nhập từ khóa cần xóa: ");
                            String deleteKeyword = scanner.nextLine();
                            bookService.deleteBooksContaining(deleteKeyword);
                            break;
                        default:
                            System.out.println("Lựa chọn không hợp lệ.");
                    }
                    break;
                case 0:
                    System.out.println("Thoát chương trình.");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }
}
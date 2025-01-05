package org.example.controller;

import org.example.model.Student;
import org.example.service.StudentService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class StudentController {

    private final Scanner scanner = new Scanner(System.in);

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    public void addStudent() {
        scanner.nextLine(); // Consume newline
        System.out.print("Nhập tên: ");
        String name = scanner.nextLine();
        System.out.print("Nhập email: ");
        String email = scanner.nextLine();
        System.out.print("Nhập ngày sinh (yyyy-mm-dd): ");
        LocalDate dob = LocalDate.parse(scanner.nextLine());
        Student student = new Student(name, email, dob);
        studentService.insertStudent(student);
    }

    public void displayAllStudents() {
        System.out.println("=== Toàn bộ danh sách học viên ===");
        studentService.getAllStudents().forEach(System.out::println);
    }

    public void searchStudentByName() {
        scanner.nextLine(); // Consume newline
        System.out.print("Nhập tên cần tìm: ");
        String keyword = scanner.nextLine();
        System.out.println("\n=== Học viên có tên chứa '" + keyword + "' ===");
        studentService.getStudentsByName(keyword).forEach(System.out::println);
    }

    public void displayStudentsSortedByDOB() {
        System.out.println("\n=== Danh sách học viên theo ngày sinh tăng dần ===");
        studentService.getStudentsSortedByDOB().forEach(System.out::println);
    }

    public void updateStudentEmail() {
        System.out.print("Nhập ID học viên: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Nhập email mới: ");
        String newEmail = scanner.nextLine();
        studentService.updateStudentEmail(id, newEmail);
    }

    public void deleteStudent() {
        System.out.print("Nhập ID học viên cần xóa: ");
        int deleteId = scanner.nextInt();
        System.out.println("Bạn có muốn xoá học viên có ID = " + deleteId + " không? (Y/N)");
        scanner.nextLine(); // Consume newline
        String confirm = scanner.nextLine();
        if (confirm.equalsIgnoreCase("Y"))
            studentService.deleteStudent(deleteId);
        else
            System.out.println("Không xoá học viên nào.");
    }

    // bài 9
    public void searchStudents() throws SQLException {
        while (true) {
            System.out.println("\n=== QUẢN LÝ HỌC VIÊN ===");
            System.out.println("1. Tìm kiếm học viên theo tên hoặc email");
            System.out.println("0. Thoát");
            System.out.print("Chọn chức năng: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Nhập tên học viên (hoặc phần tên): ");
                    String name = scanner.nextLine();
                    System.out.print("Nhập email học viên (hoặc phần email): ");
                    String email = scanner.nextLine();

                    List<Student> foundStudents = studentService.searchStudents(name, email);
                    if (foundStudents.isEmpty()) {
                        System.out.println("Không tìm thấy học viên nào.");
                    } else {
                        System.out.println("Kết quả tìm kiếm:");
                        foundStudents.forEach(System.out::println);
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


    public void getStudentsByPagination() throws SQLException {
        final int PAGE_SIZE = 5;
        int currentPage = 1;

        while (true) {
            int totalPages = studentService.getTotalPages(PAGE_SIZE);
            System.out.println("\n=== Danh sách học viên ===");
            List<Student> students = studentService.getStudentsByPagination(currentPage, PAGE_SIZE);
            students.forEach(System.out::println);

            System.out.println("\nTrang " + currentPage + " / " + totalPages);

            System.out.println("\n1. Trang trước");
            System.out.println("2. Trang tiếp theo");
            System.out.println("3. Chọn trang số...");
            System.out.println("0. Thoát");

            System.out.print("Chọn chức năng: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    if (currentPage > 1) {
                        currentPage--;
                    }
                    break;
                case 2:
                    if (currentPage < totalPages) {
                        currentPage++;
                    }
                    break;

                case 3:
                    System.out.print("Nhập số trang muốn xem (1 đến " + totalPages + "): ");
                    int pageInput = scanner.nextInt();
                    if (pageInput >= 1 && pageInput <= totalPages) {
                        currentPage = pageInput;
                    } else {
                        System.out.println("Số trang không hợp lệ.");
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

    public void getCount() throws SQLException {
        int count = studentService.getStudentCount();
        System.out.println("Số lượng học viên: " + count);
    }

    public void getMinDOB () throws SQLException {
        Student minDOBStudent = studentService.getStudentWithMinDOB();
        if (minDOBStudent != null) {
            System.out.println("Học viên có ngày sinh nhỏ nhất: " + minDOBStudent);
        } else {
            System.out.println("Không tìm thấy học viên.");
        }
    }

    public void getMaxDOB() throws SQLException {
        Student maxDOBStudent = studentService.getStudentWithMaxDOB();
        if (maxDOBStudent != null) {
            System.out.println("Học viên có ngày sinh lớn nhất: " + maxDOBStudent);
        } else {
            System.out.println("Không tìm thấy học viên.");
        }
    }
}

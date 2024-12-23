package org.example;

import org.example.model.Book;
import org.example.model.Student;
import org.example.service.BookService;
import org.example.service.StudentService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class View {
    private final Scanner scanner;
    private final StudentService studentService;
    private final BookService bookService;

    public View(StudentService studentService, BookService bookService) {
        this.studentService = studentService;
        this.bookService = bookService;
        this.scanner = new Scanner(System.in);
    }

    public void showMainMenu() throws SQLException {
        while (true) {
            System.out.println("\n=== QUẢN LÝ HỌC VIÊN ===");
            System.out.println("1. Thêm học viên");
            System.out.println("2. Hiển thị toàn bộ danh sách học viên");
            System.out.println("3. Tìm học viên theo tên");
            System.out.println("4. Danh sách học viên theo thứ tự tăng dần");
            System.out.println("5. Cập nhật email học viên");
            System.out.println("6. Xóa học viên");
            System.out.println("7. Quản lý sách");
            System.out.println("8. Phân trang học viên");
            System.out.println("9. Thống kê học viên");
            System.out.println("10. Tìm kiếm động - Seach Engine");
            System.out.println("0. Thoát");
            System.out.print("Chọn chức năng: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    displayAllStudents();
                    break;
                case 3:
                    searchStudentByName();
                    break;
                case 4:
                    displayStudentsSortedByDOB();
                    break;
                case 5:
                    updateStudentEmail();
                    break;
                case 6:
                    deleteStudent();
                    break;
                case 7:
                    showBookManagement();
                    break;
                case 8:
                    getStudentsByPagination();
                    break;
                case 9:
                    statisticalStudent();
                    break;
                case 10:
                    searchStudents();
                    break;
                case 0:
                    System.out.println("Thoát chương trình.");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }
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


    // bài 8
    public void statisticalStudent() throws SQLException {
        while (true) {
            System.out.println("\n=== THỐNG KÊ HỌC VIÊN ===");
            System.out.println("1. Đếm số lượng học viên");
            System.out.println("2. Hiển thị học viên có ngày sinh nhỏ nhất");
            System.out.println("3. Hiển thị học viên có ngày sinh lớn nhất");
            System.out.println("0. Thoát");
            System.out.print("Chọn chức năng: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    int count = studentService.getStudentCount();
                    System.out.println("Số lượng học viên: " + count);
                    break;
                case 2:
                    Student minDOBStudent = studentService.getStudentWithMinDOB();
                    if (minDOBStudent != null) {
                        System.out.println("Học viên có ngày sinh nhỏ nhất: " + minDOBStudent);
                    } else {
                        System.out.println("Không tìm thấy học viên.");
                    }
                    break;
                case 3:
                    Student maxDOBStudent = studentService.getStudentWithMaxDOB();
                    if (maxDOBStudent != null) {
                        System.out.println("Học viên có ngày sinh lớn nhất: " + maxDOBStudent);
                    } else {
                        System.out.println("Không tìm thấy học viên.");
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

    private void addStudent() {
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

    private void displayAllStudents() {
        System.out.println("=== Toàn bộ danh sách học viên ===");
        studentService.getAllStudents().forEach(System.out::println);
    }

    private void searchStudentByName() {
        scanner.nextLine(); // Consume newline
        System.out.print("Nhập tên cần tìm: ");
        String keyword = scanner.nextLine();
        System.out.println("\n=== Học viên có tên chứa '" + keyword + "' ===");
        studentService.getStudentsByName(keyword).forEach(System.out::println);
    }

    private void displayStudentsSortedByDOB() {
        System.out.println("\n=== Danh sách học viên theo ngày sinh tăng dần ===");
        studentService.getStudentsSortedByDOB().forEach(System.out::println);
    }

    private void updateStudentEmail() {
        System.out.print("Nhập ID học viên: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Nhập email mới: ");
        String newEmail = scanner.nextLine();
        studentService.updateStudentEmail(id, newEmail);
    }

    private void deleteStudent() {
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

    private void showBookManagement() {
        System.out.println("=== QUẢN LÝ SÁCH ===");
        System.out.println("1. Thêm sách");
        System.out.println("2. Hiển thị toàn bộ sách theo ngày");
        System.out.println("3. Cập nhật tên sách");
        System.out.println("4. Xóa sách theo tên");
        System.out.print("Chọn chức năng: ");
        int bookChoice = scanner.nextInt();
        switch (bookChoice) {
            case 1:
                addBook();
                break;
            case 2:
                displayAllBooks();
                break;
            case 3:
                updateBookTitle();
                break;
            case 4:
                deleteBooksByTitle();
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ.");
        }
    }

    private void addBook() {
        scanner.nextLine(); // Consume newline
        System.out.print("Nhập tên sách: ");
        String title = scanner.nextLine();
        System.out.print("Nhập tên tác giả: ");
        String author = scanner.nextLine();
        System.out.print("Nhập ngày xuất bản (yyyy-mm-dd): ");
        LocalDate publishedDate = LocalDate.parse(scanner.nextLine());
        Book newBook = new Book(title, author, publishedDate);
        bookService.addBook(newBook);
    }

    private void displayAllBooks() {
        System.out.println("=== Toàn bộ danh sách sách ===");
        bookService.getAllBooksSorted().forEach(System.out::println);
    }

    private void updateBookTitle() {
        System.out.print("Nhập ID sách cần cập nhật: ");
        int bookId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Nhập tên mới: ");
        String newTitle = scanner.nextLine();
        bookService.updateBookTitle(bookId, newTitle);
    }

    private void deleteBooksByTitle() {
        scanner.nextLine(); // Consume newline
        System.out.print("Nhập từ khóa cần xóa: ");
        String deleteKeyword = scanner.nextLine();
        bookService.deleteBooksContaining(deleteKeyword);
    }
}


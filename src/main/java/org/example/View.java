package org.example;

import org.example.controller.BookController;
import org.example.controller.RentBookController;
import org.example.controller.StudentController;

import java.sql.SQLException;
import java.util.Scanner;

public class View {
    private final Scanner scanner = new Scanner(System.in);
    private final StudentController studentController;
    private final BookController bookController;
    private final RentBookController rentBookController;

    public View(StudentController studentController, BookController bookController, RentBookController rentBookController) {
        this.studentController = studentController;
        this.bookController = bookController;
        this.rentBookController = rentBookController;
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
            System.out.println("11. Mượn sách");
            System.out.println("0. Thoát");
            System.out.print("Chọn chức năng: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    studentController.addStudent();
                    break;
                case 2:
                    studentController.displayAllStudents();
                    break;
                case 3:
                    studentController.searchStudentByName();
                    break;
                case 4:
                    studentController.displayStudentsSortedByDOB();
                    break;
                case 5:
                    studentController.updateStudentEmail();
                    break;
                case 6:
                    studentController.deleteStudent();
                    break;
                case 7:
                    bookManagementView();
                    break;
                case 8:
                    studentController.getStudentsByPagination();
                    break;
                case 9:
                    statisticalStudentView();
                    break;
                case 10:
                    studentController.searchStudents();
                    break;
                case 11:
                    setRentBookService();
                    break;
                case 0:
                    System.out.println("Thoát chương trình.");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }

    // bài 10
    private void setRentBookService() {
        while (true) {
            System.out.println("\n=== QUẢN LÝ ĐĂNG KÝ MƯỢN SÁCH ===");
            System.out.println("1. Mượn sách cho học viên");
            System.out.println("2. Hiển thị sách đã mượn của học viên");
            System.out.println("3. Cập nhật ngày mượn sách");
            System.out.println("0. Thoát");
            System.out.print("Chọn chức năng: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    rentBookController.rentBook();
                    break;
                case 2:
                    rentBookController.showRentedBooks();
                    break;
                case 3:
                    rentBookController.updateRentDate();
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
    private void statisticalStudentView() throws SQLException {
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
                    studentController.getCount();
                    break;
                case 2:
                    studentController.getMinDOB();
                    break;
                case 3:
                    studentController.getMaxDOB();
                    break;
                case 0:
                    System.out.println("Thoát chương trình.");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }


    private void bookManagementView() {
        System.out.println("=== QUẢN LÝ SÁCH ===");
        System.out.println("1. Thêm sách");
        System.out.println("2. Hiển thị toàn bộ sách theo ngày");
        System.out.println("3. Cập nhật tên sách");
        System.out.println("4. Xóa sách theo tên");
        System.out.print("Chọn chức năng: ");
        int bookChoice = scanner.nextInt();
        switch (bookChoice) {
            case 1:
                bookController.addBook();
                break;
            case 2:
                bookController.displayAllBooks();
                break;
            case 3:
                bookController.updateBookTitle();
                break;
            case 4:
                bookController.deleteBooksByTitle();
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ.");
        }
    }
}


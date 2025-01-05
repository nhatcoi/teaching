package org.example.controller;

import org.example.service.RentBookService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class RentBookController {

    private final Scanner scanner = new Scanner(System.in);
    private final RentBookService rentBookService;

    public RentBookController(RentBookService rentBookService) {
        this.rentBookService = rentBookService;
    }


    public void rentBook() {
        System.out.print("Nhập ID học viên: ");
        int studentId = scanner.nextInt();
        System.out.print("Nhập ID sách: ");
        int bookId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Nhập ngày mượn sách (yyyy-mm-dd): ");
        LocalDate rentDate = LocalDate.parse(scanner.nextLine());

        try {
            rentBookService.rentBook(studentId, bookId, rentDate);
            System.out.println("Mượn sách thành công!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Lỗi khi mượn sách.");
        }
    }

    public void showRentedBooks() {
        System.out.print("Nhập ID học viên để xem sách đã mượn: ");
        int studentIdToView = scanner.nextInt();
        try {
            List<String> rentedBooks = rentBookService.getRentedBooksByStudent(studentIdToView);
            if (rentedBooks.isEmpty()) {
                System.out.println("Học viên chưa mượn sách nào.");
            } else {
                System.out.println("Danh sách sách học viên đã mượn:");
                rentedBooks.forEach(System.out::println);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Lỗi khi lấy thông tin sách đã mượn.");
        }
    }

    public void updateRentDate() {
        System.out.print("Nhập ID đơn mượn cần cập nhật: ");
        int rentId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Nhập ngày mượn mới (yyyy-mm-dd): ");
        LocalDate newRentDate = LocalDate.parse(scanner.nextLine());

        try {
            rentBookService.updateRentDate(rentId, newRentDate);
            System.out.println("Cập nhật ngày mượn sách thành công!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Lỗi khi cập nhật ngày mượn sách.");
        }
    }
}

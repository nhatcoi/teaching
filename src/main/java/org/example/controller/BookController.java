package org.example.controller;

import org.example.model.Book;
import org.example.service.BookService;

import java.time.LocalDate;
import java.util.Scanner;

public class BookController {

    private final Scanner scanner = new Scanner(System.in);
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    public void addBook() {
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

    public void displayAllBooks() {
        System.out.println("=== Toàn bộ danh sách sách ===");
        bookService.getAllBooksSorted().forEach(System.out::println);
    }

    public void updateBookTitle() {
        System.out.print("Nhập ID sách cần cập nhật: ");
        int bookId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Nhập tên mới: ");
        String newTitle = scanner.nextLine();
        bookService.updateBookTitle(bookId, newTitle);
    }

    public void deleteBooksByTitle() {
        scanner.nextLine(); // Consume newline
        System.out.print("Nhập từ khóa cần xóa: ");
        String deleteKeyword = scanner.nextLine();
        bookService.deleteBooksContaining(deleteKeyword);
    }

}

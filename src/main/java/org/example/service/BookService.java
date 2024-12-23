package org.example.service;

import org.example.model.Book;

import java.util.List;

public interface BookService {
    void addBook(Book book);
    List<Book> getAllBooksSorted();
    void updateBookTitle(int id, String newTitle);
    void deleteBooksContaining(String keyword);
}

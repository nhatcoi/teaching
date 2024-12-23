package org.example.service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface RentBookService {

    // bài 10 - mượn sách - done

    // Thêm bản ghi vào bảng rents
    void rentBook(int studentId, int bookId, LocalDate rentDate) throws SQLException;

    // Hiển thị danh sách học viên và các sách đã mượn
    List<String> getRentedBooksByStudent(int studentId) throws SQLException;

    // Cập nhật thông tin đơn mượn sách
    void updateRentDate(int rentId, LocalDate newRentDate) throws SQLException;
}

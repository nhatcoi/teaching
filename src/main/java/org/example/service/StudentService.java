package org.example.service;

import org.example.model.Student;

import java.sql.SQLException;
import java.util.List;

// interface StudentService thể hiện trừu tượng hóa các phương thức CRUD
public interface StudentService {
    // create student
    void insertStudent(Student student);

    // read student

    List<Student> getAllStudents();

    List<Student> getStudentsByName(String keyword);
    List<Student> getStudentsSortedByDOB();

    // update student
    void updateStudentEmail(int id, String newEmail);

    // delete student
    void deleteStudent(int id);

    List<Student> getStudentsByPagination(int page, int pageSize) throws SQLException;

    int getTotalPages(int pageSize) throws SQLException;
}

package org.example.service;

import org.example.model.Student;

import java.util.List;

// interface StudentService thể hiện trừu tượng hóa các phương thức CRUD
public interface StudentService {
    // create student
    void insertStudent(Student student);

}

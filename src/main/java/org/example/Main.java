package org.example;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {

//        try {
//            // ket noi voi db
//            Connection conn = Database.getConnection();
//            System.out.println("Connected to database successfully : " + conn);
//
//            // select pet
//            String sql = "SELECT * FROM pets";
//            PreparedStatement preparedStatement = conn.prepareStatement(sql);
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            while(resultSet.next()) {
//                int id = resultSet.getInt("id");
//                String name = resultSet.getString("name");
//                Timestamp date = resultSet.getTimestamp("dob");
//
//                System.out.println(
//                        "ID: " + id + ", Name: " + name + ", Date: " + date + "\n"
//                );
//            }
//
//            // insert - pets
//
//
//            // click 1 - add - Bird
////            String sqlInsertData = "INSERT INTO pets (name, dob) VALUES (?, ?)";
////            preparedStatement = conn.prepareStatement(sqlInsertData);
////            preparedStatement.setString(1, "Bird");
////            preparedStatement.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
////            int rowInserted = preparedStatement.executeUpdate();
////            System.out.println("Row inserted: " + rowInserted);
//
//            // update
//            String sqlUpdateData = "UPDATE pets SET name = ? WHERE id = ?";
//            preparedStatement = conn.prepareStatement(sqlUpdateData);
//            preparedStatement.setString(1, "Shiba Dog");
//            preparedStatement.setInt(2, 1);
//            preparedStatement.executeUpdate();
//
//            // delete
//            String sqlDeleteData = "DELETE FROM pets WHERE id = ?";
//            preparedStatement = conn.prepareStatement(sqlDeleteData);
//            preparedStatement.setInt(1, 3);
//            preparedStatement.executeUpdate();
//
//        } catch (SQLException e) {
//            System.out.println("Error: " + e.getMessage());
//        }



        // inject service into main
        StudentService studentService = new StudentService();


        System.out.println("Menu");
        System.out.println("1. Them Sinh vien");
        System.out.println("2. Danh sach sinh vien");
        System.out.println("3. Tim kiem sinh vien");
        System.out.println("4. Sua sinh vien");
        System.out.println("5. Xoa sinh vien");

        Scanner scanner = new Scanner(System.in);
        int option;
        System.out.print("Chon tinh nang: ");
        option = scanner.nextInt();

        switch (option) {
            case 1: {
                Student newStudent = new Student();
                System.out.print("Ten sinh vien: ");
                String name = scanner.nextLine();
                newStudent.setName(name);
                scanner.nextLine();

                System.out.print("Email sinh vien: ");
                String email = scanner.nextLine();
                newStudent.setEmail(email);
                System.out.print("Ngay sinh: ");

                String dateString = scanner.nextLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                Date dateOfBirth = Date.valueOf(LocalDate.parse(dateString, formatter));
                newStudent.setDob(dateOfBirth);

                studentService.addStudent(newStudent);
                break;
            }

            case 2: {
                System.out.println("Danh sach sinh vien:");
                System.out.println(studentService.getAllStudents());
                break;
            }


        }


    }
}
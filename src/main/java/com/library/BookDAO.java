package com.library;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/library_db?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";        
    private static final String PASSWORD = "Razigithub2005";

    public BookDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); 
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addBook(String title, String author, String publisher, int year) {
        String sql = "INSERT INTO books (title, author, publisher, year) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, title);
            pstmt.setString(2, author);
            pstmt.setString(3, publisher);
            pstmt.setInt(4, year);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Kitab uğurla əlavə edildi.");
            } else {
                System.out.println("Kitab əlavə olunarkən problem yarandı.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void listBooks() {
        String sql = "SELECT * FROM books";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\nKitablar:");
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String author = rs.getString("author");
                String publisher = rs.getString("publisher");
                int year = rs.getInt("year");

                System.out.printf("%d: %s, Müəllif: %s, Nəşriyyat: %s, İl: %d\n",
                        id, title, author, publisher, year);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    public boolean updateBook(int id, String title, String author, String publisher, int year) {
        String sql = "UPDATE books SET title = ?, author = ?, publisher = ?, year = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, title);
            pstmt.setString(2, author);
            pstmt.setString(3, publisher);
            pstmt.setInt(4, year);
            pstmt.setInt(5, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean deleteBook(int id) {
        String sql = "DELETE FROM books WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


}

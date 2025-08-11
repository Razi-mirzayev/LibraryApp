package com.library;

import java.util.Scanner;

public class LibraryApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BookDAO bookDAO = new BookDAO();

        while (true) {
            System.out.println("\n--- Kitabxana Menyusu ---");
            System.out.println("1. Kitab əlavə et");
            System.out.println("2. Kitabları göstər");
            System.out.println("3. Çıxış");
            System.out.print("Seçim: ");

            int choice = sc.nextInt();
            sc.nextLine(); // buffer təmizləmək üçün

            switch (choice) {
                case 1:
                    System.out.print("Kitab adı: ");
                    String title = sc.nextLine();
                    System.out.print("Müəllif: ");
                    String author = sc.nextLine();
                    System.out.print("Nəşriyyat: ");
                    String publisher = sc.nextLine();
                    System.out.print("İl: ");
                    int year = sc.nextInt();
                    bookDAO.addBook(title, author, publisher, year);
                    break;
                case 2:
                    bookDAO.listBooks();
                    break;
                case 3:
                    System.out.println("Çıxılır...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Yanlış seçim!");
            }
        }
    }
}


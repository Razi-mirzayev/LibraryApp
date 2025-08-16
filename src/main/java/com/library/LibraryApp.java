package com.library;

import java.util.List;
import java.util.Scanner;

public class LibraryApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UserDAO userDAO = new UserDAO();
        BookDAO bookDAO = new BookDAO();
        BorrowDAO borrowDAO = new BorrowDAO();

        System.out.println("Kitabxanaya xoş gəldiniz!");
        User currentUser = null;

        // Login / qeydiyyat
        while (currentUser == null) {
            System.out.println("\n1. Daxil ol");
            System.out.println("2. Qeydiyyatdan keç");
            System.out.print("Seçim: ");
            int option = sc.nextInt();
            sc.nextLine();

            if (option == 1) {
                System.out.print("İstifadəçi adı: ");
                String username = sc.nextLine();
                System.out.print("Şifrə: ");
                String password = sc.nextLine();

                currentUser = userDAO.login(username, password);
                if (currentUser != null) {
                    System.out.println("Xoş gəldiniz, " + currentUser.getUsername() + " (" + currentUser.getRole() + ")");
                } else {
                    System.out.println("Yanlış məlumat!");
                }
            } else if (option == 2) {
                System.out.print("İstifadəçi adı: ");
                String username = sc.nextLine();
                System.out.print("Şifrə: ");
                String password = sc.nextLine();
                System.out.print("Rol (admin/user): ");
                String role = sc.nextLine();

                if (userDAO.register(username, password, role)) {
                    System.out.println("Qeydiyyat uğurla tamamlandı!");
                } else {
                    System.out.println("Xəta baş verdi!");
                }
            }
        }

        // Əsas menyu
        while (true) {
            if (currentUser.getRole().equals("admin")) {
                System.out.println("\n--- Admin Menyusu ---");
                System.out.println("1. Kitab əlavə et");
                System.out.println("2. Kitabları göstər");
                System.out.println("3. Kitab yenilə");
                System.out.println("4. Kitab sil");
                System.out.println("5. Kitab axtar");
                System.out.println("6. Çıxış");
            } else { // user
                System.out.println("\n--- İstifadəçi Menyusu ---");
                System.out.println("1. Kitabları göstər");
                System.out.println("2. Kitab axtar");
                System.out.println("3. Kitab götür");
                System.out.println("4. Kitabı qaytar");
                System.out.println("5. Çıxış");
            }

            System.out.print("Seçim: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    if (currentUser.getRole().equals("admin")) {
                        // Admin: kitab əlavə et
                        System.out.print("Kitab adı: ");
                        String title = sc.nextLine();
                        System.out.print("Müəllif: ");
                        String author = sc.nextLine();
                        System.out.print("Nəşriyyat: ");
                        String publisher = sc.nextLine();
                        System.out.print("İl: ");
                        int year = sc.nextInt();
                        sc.nextLine();

                        Book newBook = new Book(0, title, author, publisher, year, "mövcud");
                        if (bookDAO.addBook(newBook)) System.out.println("✅ Kitab əlavə olundu.");
                        else System.out.println("❌ Xəta baş verdi.");
                    } else {
                        // User: kitabları göstər
                        List<Book> books = bookDAO.listBooks();
                        if (books.isEmpty()) System.out.println("Kitab yoxdur.");
                        else books.forEach(System.out::println);
                    }
                    break;

                case 2:
                    System.out.print("Axtarış sözü: ");
                    String keyword = sc.nextLine();
                    List<Book> searchResults = bookDAO.searchBooks(keyword);
                    if (searchResults.isEmpty()) System.out.println("Heç bir nəticə tapılmadı.");
                    else searchResults.forEach(System.out::println);
                    break;

                case 3:
                    if (currentUser.getRole().equals("admin")) {
                        // Admin: kitab yenilə
                        System.out.print("Yenilənəcək kitabın ID-si: ");
                        int updateId = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Yeni ad: ");
                        String newTitle = sc.nextLine();
                        System.out.print("Yeni müəllif: ");
                        String newAuthor = sc.nextLine();
                        System.out.print("Yeni nəşriyyat: ");
                        String newPublisher = sc.nextLine();
                        System.out.print("Yeni il: ");
                        int newYear = sc.nextInt();
                        sc.nextLine();

                        Book updatedBook = new Book(updateId, newTitle, newAuthor, newPublisher, newYear, "mövcud");
                        if (bookDAO.updateBook(updatedBook)) System.out.println("✅ Kitab yeniləndi.");
                        else System.out.println("❌ Yeniləmə uğursuz oldu.");
                    } else {
                        // User: kitab götür
                        System.out.print("Götürmək istədiyiniz kitabın ID-si: ");
                        int borrowId = sc.nextInt();
                        sc.nextLine();
                        Book bookToBorrow = bookDAO.getBookById(borrowId);
                        if (bookToBorrow != null && bookToBorrow.getStatus().equals("mövcud")) {
                            bookToBorrow.setStatus("götürülüb");
                            bookDAO.updateBook(bookToBorrow);
                            borrowDAO.addBorrowHistory(currentUser.getId(), borrowId);
                            System.out.println("✅ Kitab uğurla götürüldü!");
                        } else System.out.println("❌ Kitab mövcud deyil!");
                    }
                    break;

                case 4:
                    if (currentUser.getRole().equals("admin")) {
                        // Admin: kitab sil
                        System.out.print("Silinəcək kitabın ID-si: ");
                        int deleteId = sc.nextInt();
                        sc.nextLine();
                        if (bookDAO.deleteBook(deleteId)) System.out.println("✅ Kitab silindi.");
                        else System.out.println("❌ Silmə uğursuz oldu.");
                    } else {
                        // User: kitab qaytar
                        System.out.print("Qaytarmaq istədiyiniz kitabın ID-si: ");
                        int returnId = sc.nextInt();
                        sc.nextLine();
                        Book bookToReturn = bookDAO.getBookById(returnId);
                        if (bookToReturn != null && bookToReturn.getStatus().equals("götürülüb")) {
                            bookToReturn.setStatus("mövcud");
                            bookDAO.updateBook(bookToReturn);
                            borrowDAO.returnBook(currentUser.getId(), returnId);
                            System.out.println("✅ Kitab uğurla qaytarıldı!");
                        } else System.out.println("❌ Kitab qaytarıla bilməz!");
                    }
                    break;

                case 5:
                    System.out.println("Çıxılır...");
                    sc.close();
                    System.exit(0);
                    break;

                case 6:
                    if (currentUser.getRole().equals("admin")) {
                        System.out.println("Çıxılır...");
                        sc.close();
                        System.exit(0);
                    } else {
                        System.out.println("Yanlış seçim!");
                    }
                    break;

                default:
                    System.out.println("Yanlış seçim!");
            }
        }
    }
}

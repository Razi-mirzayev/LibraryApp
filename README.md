LibraryApp
LibraryApp — Java və MySQL istifadə edilərək hazırlanmış sadə kitabxana idarəetmə sistemi.

Layihə haqqında
Bu layihə vasitəsilə istifadəçi konsoldan kitab əlavə edə, kitabların siyahısını görə və proqramdan çıxa bilər. Layihə MySQL verilənlər bazası ilə əlaqələndirilmişdir və kitab məlumatlarını books cədvəlində saxlayır.

Texnologiyalar
Java 17
MySQL
Maven
MySQL Connector/J (JDBC Driver)

Verilənlər Bazası
Layihənin işləməsi üçün library_db adlı MySQL verilənlər bazası yaradılmalıdır və aşağıdakı kimi books cədvəli olmalıdır:

sql
CREATE DATABASE IF NOT EXISTS library_db;
USE library_db;

CREATE TABLE IF NOT EXISTS books (
  id INT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  author VARCHAR(255) NOT NULL,
  publisher VARCHAR(255),
  year INT
);

Layihənin qurulması və işə salınması
MySQL verilənlər bazasını yaradın və yuxarıdakı cədvəl strukturunu tətbiq edin.

DBConnection və BookDAO siniflərində istifadəçi adı və şifrə (root və Razigithub2005) öz serverinizdəki məlumatlarla əvəz edin.

Layihəni Maven ilə yükləyin:

bash
mvn clean install
Layihəni işə salın:

bash
mvn exec:java -Dexec.mainClass="com.library.LibraryApp"
və ya IDE-də LibraryApp sinifinin main metodunu çalışdırın.

İstifadəçi Menyusu
1 - Yeni kitab əlavə etmək
2 - Kitabların siyahısını göstərmək
3 - Proqramdan çıxmaq

Gələcək inkişaflar
Kitab silmək və yeniləmək funksiyalarının əlavə edilməsi
Axtarış funksiyası
GUI (Qrafik İstifadəçi İnterfeysi) əlavə edilməsi

Lisenziya
Bu layihə açıq mənbədir və sərbəst istifadə üçün nəzərdə tutulub.

Hazırlayan: Razi Mirzəyev











Ask ChatGPT

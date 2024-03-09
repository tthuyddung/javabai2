package com.example.demo5;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class bai2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập đường dẫn thư mục hoặc tệp tin cần xóa:");
        String pathString = scanner.nextLine();

        Path path = Paths.get(pathString);

        try {
            if (Files.exists(path)) {
                if (Files.isDirectory(path)) {
                    deleteDirectory(path);
                } else {
                    deleteFile(path);
                }
                System.out.println("Xóa thành công.");
            } else {
                System.out.println("Đường dẫn không tồn tại.");
            }
        } catch (Exception e) {
            System.out.println("Đã xảy ra lỗi: " + e.getMessage());
        }
    }

    private static void deleteDirectory(Path path) throws Exception {
        Files.walk(path)
                .sorted((p1, p2) -> -p1.compareTo(p2))
                .forEach(p -> {
                    try {
                        Files.delete(p);
                    } catch (Exception e) {
                        throw new RuntimeException(e.getMessage());
                    }
                });
    }

    private static void deleteFile(Path path) throws Exception {
        Files.delete(path);
    }
}
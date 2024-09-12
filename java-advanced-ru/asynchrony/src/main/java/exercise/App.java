package exercise;

import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

class App {

    public static CompletableFuture<String> unionFiles(String file1Path, String file2Path, String destFilePath) {
        return CompletableFuture.supplyAsync(() -> {
            try {

                String content1 = Files.readString(Path.of(file1Path));

                String content2 = Files.readString(Path.of(file2Path));

                String combinedContent = content1 + System.lineSeparator() + content2;


                Path destPath = Path.of(destFilePath);
                Files.writeString(destPath, combinedContent, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

                return "Файлы объединены и записаны в: " + destFilePath;
            } catch (IOException e) {

                return "Ошибка: " + e.getMessage();
            }
        });
    }


    public static void main(String[] args) {
        try {

            CompletableFuture<String> result = App.unionFiles("src/main/resources/file1.txt", "src/main/resources/file2.txt", "src/main/resources/dest.txt");


            System.out.println(result.get());
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Ошибка при выполнении: " + e.getMessage());
        }
    }
    }



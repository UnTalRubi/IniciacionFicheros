package edu.teis;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {

        System.out.println("Introduce un directorio:");
        Scanner scanner = new Scanner(System.in);
        String dirRoute = scanner.nextLine();
        Path path = Path.of(dirRoute);

        System.out.println("Contenido de " + dirRoute);
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
            for (Path file : stream) {

                String fileType;

                if (Files.isDirectory(file)) fileType = "d";
                else fileType = "-";

                String filePermits =
                        (Files.isReadable(file) ? "r" : "-") +
                        (Files.isWritable(file) ? "w" : "-") +
                        (Files.isExecutable(file) ? "e" : "-");

                System.out.println(fileType + filePermits + "\t" + file.getFileName());
            }
        }
    }
}
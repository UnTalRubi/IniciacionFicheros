package edu.teis.ls;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Ls {
    public static void main(String[] args){

        System.out.println("Introduce un directorio:");
        Scanner scanner = new Scanner(System.in);
        String dirRoute = scanner.nextLine();
        Path path = Path.of(dirRoute);

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
            System.out.println("Contenido de " + dirRoute);
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
        }catch (IOException e){
            System.err.println("Directorio inválido: " + e.getMessage());
        }
    }
}
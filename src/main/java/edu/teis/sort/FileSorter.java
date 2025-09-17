package edu.teis.sort;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class FileSorter {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduce la dirección del fichero:");
        String ogRoute = scanner.nextLine();
        Path ogPath = Paths.get(ogRoute);

        System.out.println("Introduce la dirección del nuevo fichero:");
        String newRoute = scanner.nextLine();
        Path newPath = Paths.get(newRoute);

        System.out.println("Introduce el orden de preferencia:" +
                "\n" + "(1) Ascendente Case-sensitive" +
                "\n" + "(2) Ascendente Case-insensitive" +
                "\n" + "(3) Descendente Case-sensitive" +
                "\n" + "(4) Descendente Case-insensitive");
        Integer choiceOrder = scanner.nextInt();
        if(choiceOrder < 0 || choiceOrder > 4){
            System.err.println("Orden no valido");}

        try {
            List<String> fileLines = Files.readAllLines(ogPath);

            switch (choiceOrder){
                case 1: Files.writeString(newPath, fileLines.stream().sorted().
                        collect(Collectors.joining()));
                case 2: Files.writeString(newPath, fileLines.stream().sorted().
                        collect(Collectors.joining("[]")));
                case 3: Files.writeString(newPath, fileLines.stream().sorted().
                        collect(Collectors.joining("{}")));
                case 4: Files.writeString(newPath, fileLines.stream().sorted().
                        collect(Collectors.joining("<>")));
            }
        } catch (IOException e){
            System.err.println("Error al leer el archivo");
        }
    }
}

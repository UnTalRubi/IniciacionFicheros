package edu.teis.sort;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class FileSorter {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduce la dirección y nombre del fichero:");
        String ogRoute = scanner.nextLine();
        Path ogPath = Paths.get(ogRoute);

        String fileName = ogPath.getFileName().toString();
        String nameNoExtension = fileName.substring(0, fileName.lastIndexOf('.'));

        System.out.println("Introduce la dirección del nuevo fichero:");
        String newRoute = scanner.nextLine();
        Path newPath = Paths.get(newRoute);

        System.out.println("Introduce el orden de preferencia:" +
                "\n(1) Ascendente Case-sensitive" +
                "\n(2) Ascendente Case-insensitive" +
                "\n(3) Descendente Case-sensitive" +
                "\n(4) Descendente Case-insensitive");
        int choiceOrder = scanner.nextInt();
        if(choiceOrder < 0 || choiceOrder > 4){
            System.err.println("Orden no valido");}

        try {
            List<String> fileLines = Files.readAllLines(ogPath);

            Files.createDirectories(newPath);

            Path outputFile;
            List<String> sortedLines;

            switch (choiceOrder) {
                case 1: sortedLines = fileLines.stream()
                            .sorted()
                            .collect(Collectors.toList());
                    outputFile = newPath.resolve(nameNoExtension + "_asc_case.txt");
                    break;
                case 2: sortedLines = fileLines.stream()
                            .sorted(String.CASE_INSENSITIVE_ORDER)
                            .collect(Collectors.toList());
                    outputFile = newPath.resolve(nameNoExtension + "_asc_non_case.txt");
                    break;
                case 3: sortedLines = fileLines.stream()
                            .sorted(Comparator.reverseOrder())
                            .collect(Collectors.toList());
                    outputFile = newPath.resolve(nameNoExtension + "_desc_case.txt");
                    break;
                case 4: sortedLines = fileLines.stream()
                            .sorted(String.CASE_INSENSITIVE_ORDER.reversed())
                            .collect(Collectors.toList());
                    outputFile = newPath.resolve(nameNoExtension + "_desc_non_case.txt");
                    break;
                default:
                    throw new IllegalStateException("Orden inesperado");
            }

            Files.write(outputFile, sortedLines);
            System.out.println("Archivo creado en: " + outputFile);

        } catch (IOException e){
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}

package edu.teis.cars;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.stream.Stream;

public class CarGrouper {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce la dirección del fichero original: ");
        String inputFileName = sc.nextLine();

        System.out.println("Introduce la dirección del fichero de salida: ");
        String outputFileName = sc.nextLine();

        Path inputPath = Paths.get(inputFileName);
        Path outputPath = Paths.get(outputFileName);

        Map<String, List<String>> brands = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

        try (Stream<String> lines = Files.lines(inputPath, StandardCharsets.UTF_8)) {
            for (String line : (Iterable<String>) lines::iterator) {
                line = line.trim();
                if (line.isEmpty()) continue;

                int space = line.indexOf(' ');
                String brand;
                String model;
                if (space == -1) {
                    brand = line;
                    model = "";
                } else {
                    brand = line.substring(0, space);
                    model = line.substring(space + 1).trim();
                }

                brands.computeIfAbsent(brand, k -> new ArrayList<>()).add(model);
            }
        } catch (IOException e) {
            System.err.println("No se pudo encontrar " + e.getMessage());
            return;
        }

        for (List<String> models : brands.values()) {
            Collections.sort(models, String.CASE_INSENSITIVE_ORDER);
        }

        List<String> outputLines = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : brands.entrySet()) {
            String brand = entry.getKey();
            List<String> models = entry.getValue();
            outputLines.add(brand + ": " + String.join(", ", models));
        }

        try {
            Files.write(outputPath, outputLines, StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            System.out.println("Se ha completado el agrupamiento: " + outputFileName);
        } catch (IOException e) {
            System.err.println("Error al escribir en el fichero " + e.getMessage());
        }
    }
}
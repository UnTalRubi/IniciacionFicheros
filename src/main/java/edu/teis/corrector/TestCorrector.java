package edu.teis.corrector;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class TestCorrector {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduce la dirección del fichero a corregir:");
        String route = scanner.nextLine();
        Path path = Paths.get(route);

        List<String> fileLines;
        try {
            fileLines = Files.readAllLines(path);
        } catch (IOException e) {
            System.err.println("Fichero inválido: " + e.getMessage());
            return;
        }

        if (fileLines.isEmpty()) {
            System.out.println("El fichero está vacío");
            return;
        }

        String answerKey = fileLines.getFirst();
        Map<String, String> answersMap = new HashMap<>();

        for (int i = 1; i < fileLines.size(); i++) {
            String line = fileLines.get(i);
            String[] parts = line.split("\\s+", 2);
            if (parts.length == 2) {
                String studentCode = parts[0];
                String studentAnswers = parts[1];
                answersMap.put(studentCode, studentAnswers);
            }
        }

        System.out.println("Respuesta correcta: " + answerKey);
        System.out.println("Puntuaciones: ");

        for (Map.Entry<String, String> entry : answersMap.entrySet()) {
            String studentCode = entry.getKey();
            String studentAnswers = entry.getValue();

            double score = calculateScore(answerKey, studentAnswers);
            System.out.println(studentCode + " -> " + score);
        }
    }

    private static double calculateScore(String answerKey, String answers) {
        double points = 0.0;

        for (int i = 0; i < answerKey.length() && i < answers.length(); i++) {
            char correct = answerKey.charAt(i);
            char given = answers.charAt(i);

            if (given == ' ') continue;
            if (given == correct) {
                points += 0.5;
            } else {
                points -= 0.15;
            }
        }
        return points;
    }
}

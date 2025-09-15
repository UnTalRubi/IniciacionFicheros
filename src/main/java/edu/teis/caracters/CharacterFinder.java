package edu.teis.caracters;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class CharacterFinder {

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduce la dirección del fichero:");
        String route = scanner.nextLine();

        System.out.println("Introduce un carácter a buscar:");
        String character = scanner.nextLine();

        char targetChar = character.charAt(0);

        System.out.println("Buscando en " + route + " la letra " + character + "...");

        long chCount = 0;
        Path path = Paths.get(route);

        try (BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            int currentChar;
            while ((currentChar = br.read()) != -1) {
                if ((char) currentChar == targetChar) chCount++;
            }
        } catch (IOException e) {
            System.err.println("Fichero inválido: " + e.getMessage());
        }

        System.out.println("El carácter '" + targetChar + "' aparece " + chCount + " veces.");
    }
}

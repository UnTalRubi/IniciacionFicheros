package edu.teis.caracters;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class FindCaracters {

    public static void main(String[] args){

        System.out.println("Introduce la dirección del fichero:");
        Scanner scannerDir = new Scanner(System.in);
        String dirFile = scannerDir.nextLine();

        System.out.println("Introduce un carácter a buscar:");
        Scanner scannerCaracter = new Scanner(System.in);
        String caracter = scannerCaracter.nextLine();

        System.out.println(dirFile + " -> " + caracter);

        try (BufferedReader reader = new BufferedReader(new FileReader(dirFile));) {
            String txtLine;
            while ((txtLine = reader.readLine()) != null)
                System.out.println(txtLine);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}

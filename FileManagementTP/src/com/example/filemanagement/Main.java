package com.example.filemanagement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.NoSuchFileException;
import java.nio.file.attribute.BasicFileAttributes;

import java.util.List;

public class Main {

    // 1. Lecture avec BufferedReader
    public static void readFileWithBufferedReader(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line;

            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture : " + e.getMessage());
        }
    }

    // 2. Ecriture avec BufferedWriter
    public static void writeFileWithBufferedWriter(String filePath, String content) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {

            bw.write(content);
            bw.newLine();
            bw.write("Ligne ajoutée avec succès.");

        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture : " + e.getMessage());
        }
    }

    // 3. Ecriture avec PrintWriter
    public static void writeWithPrintWriter(String filePath) {
        try (PrintWriter pw = new PrintWriter(new FileOutputStream(filePath))) {

            pw.println("=== Rapport des employés ===");

            pw.printf("Nom: %s, Âge: %d, Poste: %s%n",
                    "Alice", 25, "Engineer");

            pw.printf("Nom: %s, Âge: %d, Poste: %s%n",
                    "Bob", 30, "Manager");

            pw.println("Fin du rapport.");

            if (pw.checkError()) {
                System.err.println("Erreur pendant l'écriture.");
            }

        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture : " + e.getMessage());
        }
    }

    // 4. Lecture avec l'API Files
    public static void readWithFilesApi(String filePath) {
        try {
            Path path = Paths.get(filePath);

            List<String> lines = Files.readAllLines(path);

            for (String line : lines) {
                System.out.println(line);
            }

        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture : " + e.getMessage());
        }
    }

    // 5. Ecriture avec l'API Files
    public static void writeWithFilesApi(String filePath, String content) {
        try {
            Path path = Paths.get(filePath);

            Files.write(path, content.getBytes());

            System.out.println("Fichier écrit avec succès : " + filePath);

        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture : " + e.getMessage());
        }
    }

    // 6. Copier un fichier
    public static void copyFile(String source, String destination) {
        try {
            Path sourcePath = Paths.get(source);
            Path destPath = Paths.get(destination);

            Files.copy(sourcePath, destPath,
                    StandardCopyOption.REPLACE_EXISTING);

            System.out.println("Fichier copié de " + source
                    + " vers " + destination);

        } catch (IOException e) {
            System.err.println("Erreur lors de la copie : " + e.getMessage());
        }
    }

    // 7. Déplacer un fichier
    public static void moveFile(String source, String destination) {
        try {
            Path sourcePath = Paths.get(source);
            Path destPath = Paths.get(destination);

            Files.move(sourcePath, destPath,
                    StandardCopyOption.REPLACE_EXISTING);

            System.out.println("Fichier déplacé de " + source
                    + " vers " + destination);

        } catch (IOException e) {
            System.err.println("Erreur lors du déplacement : " + e.getMessage());
        }
    }

    // 8. Supprimer un fichier
    public static void deleteFile(String filePath) {
        try {
            Path path = Paths.get(filePath);

            if (Files.exists(path)) {
                Files.delete(path);
                System.out.println("Fichier supprimé : " + filePath);
            } else {
                System.out.println("Le fichier n'existe pas : " + filePath);
            }

        } catch (NoSuchFileException e) {
            System.err.println("Le fichier a été supprimé entre-temps : "
                    + e.getMessage());

        } catch (IOException e) {
            System.err.println("Erreur lors de la suppression : "
                    + e.getMessage());
        }
    }

    // 9. Lecture d'un fichier binaire
    public static void readBinaryFile(String filePath) {
        try (BufferedInputStream bis =
                new BufferedInputStream(new FileInputStream(filePath))) {

            byte[] buffer = new byte[1024];

            int bytesRead;
            long totalBytes = 0;

            while ((bytesRead = bis.read(buffer)) != -1) {
                totalBytes += bytesRead;
            }

            System.out.println("Fichier binaire lu : " + filePath);
            System.out.println("Taille totale : " + totalBytes + " octets");

        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture binaire : "
                    + e.getMessage());
        }
    }

    // 10. Ecriture d'un fichier binaire
    public static void writeBinaryFile(String filePath, byte[] data) {
        try (BufferedOutputStream bos =
                new BufferedOutputStream(new FileOutputStream(filePath))) {

            bos.write(data);
            bos.flush();

            System.out.println("Fichier binaire écrit : " + filePath);
            System.out.println("Taille écrite : " + data.length + " octets");

        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture binaire : "
                    + e.getMessage());
        }
    }

    // 11. Récupération des métadonnées d'un fichier
    public static void getFileMetadata(String filePath) {
        try {
            Path path = Paths.get(filePath);

            BasicFileAttributes attrs = Files.readAttributes(
                    path, BasicFileAttributes.class);

            System.out.println("=== Métadonnées du fichier : "
                    + filePath + " ===");

            System.out.println("Taille : " + attrs.size() + " octets");

            System.out.println("Dernière modification : "
                    + attrs.lastModifiedTime().toInstant());

            System.out.println("Est un répertoire : "
                    + attrs.isDirectory());

            System.out.println("Est un fichier : "
                    + attrs.isRegularFile());

        } catch (IOException e) {
            System.err.println("Erreur lors de la récupération des métadonnées : "
                    + e.getMessage());
        }
    }

    // 12. Méthode principale
    public static void main(String[] args) {

        System.out.println("TP 12 : Gestion des fichiers en Java");

        // Test BufferedWriter
        writeFileWithBufferedWriter(
                "data/output.txt",
                "Bonjour ! Ceci est un test."
        );

        // Test PrintWriter
        writeWithPrintWriter("data/report.txt");

        // Test Files API : écriture
        writeWithFilesApi(
                "data/nio_output.txt",
                "Bonjour ! Ecriture avec l'API Files."
        );

        // Test Files API : lecture
        System.out.println("\nLecture des employés :");
        readWithFilesApi("data/employees.txt");

        // Test copie
        copyFile("data/employees.txt", "data/employees_copy.txt");

        // Test déplacement
        moveFile("data/employees_copy.txt", "data/employees_moved.txt");

        // Test suppression
        deleteFile("data/employees_moved.txt");

        // Test écriture binaire
        byte[] data = {10, 20, 30, 40, 50, 60, 70, 80};
        writeBinaryFile("data/binary.dat", data);

        // Test lecture binaire
        System.out.println("\nLecture binaire :");
        readBinaryFile("data/binary.dat");

        // Test métadonnées
        System.out.println("\nInformations du fichier :");
        getFileMetadata("data/employees.txt");

        System.out.println("\nToutes les opérations sont terminées.");
    }
}

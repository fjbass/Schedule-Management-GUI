package app.utils;

import app.entities.Student;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileHandler {
    public static List<String[]> readDataFromFile(String filename) {
        ArrayList<String[]> arrayList = new ArrayList<>();

        try {
            String[] fileArray = FileHandler.readArrayFromTextFile(filename);

            for (String temp : fileArray) {
                String[] array = temp.split(",");
                arrayList.add(array);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File was not found, or could not be opened");
        }
        return arrayList;
    }

    public static String[] readArrayFromTextFile(String fileName) throws FileNotFoundException {
        ArrayList<String> stringArrayList = new ArrayList<>();
        try (FileInputStream fileInStream = new FileInputStream(fileName); Scanner readFromFile = new Scanner(fileInStream)) {
            while (readFromFile.hasNext()) {
                stringArrayList.add(readFromFile.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] stringArray = new String[stringArrayList.size()];
        return stringArrayList.toArray(stringArray);
    }

    public static void updateDataFromFile(String oldString, String newString, String path) {
        try {
            List<String> fileContent = new ArrayList<>(Files.readAllLines(Path.of(path), StandardCharsets.UTF_8));
            for (int i = 0; i < fileContent.size(); i++) {
                if (fileContent.get(i).equals(oldString)) {
                    fileContent.set(i, newString);
                    break;
                }
            }

            Files.write(Path.of(path), fileContent, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteSelectedDataFromFile(Student selectedStudent, String path) {
        File inputFile = new File(path);
        File tempFile = new File("src/main/resources/domain/myTempFile.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile)); BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            String lineToRemove = selectedStudent.toString();
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                String trimmedLine = currentLine.trim();
                if (trimmedLine.equals(lineToRemove)) continue;
                writer.write(currentLine + System.getProperty("line.separator"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Files.delete(Path.of(inputFile.getPath()));
            Path source = Path.of(tempFile.getPath());
            Files.move(source, source.resolveSibling(inputFile.getName()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

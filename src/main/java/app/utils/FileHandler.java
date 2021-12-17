package app.utils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileHandler {
    public static List<String[]> readDataFromFile(String filename) {
        ArrayList<String[]> arrayList = new ArrayList<>(); //takes 1

        try {
            String[] fileArray = FileHandler.readArrayFromTextFile(filename); //takes 1

            for (String temp : fileArray) { //takes n time to loop throught the file
                String[] array = temp.split(","); //takes 1
                arrayList.add(array); //takes 1
            }
        } catch (FileNotFoundException e) {
            System.out.println("File was not found, or could not be opened"); //takes 1
        }
        return arrayList; //takes 1
    }

    //There is no recursive method, so we don't have a best case;
    //We loop n times through the file until we find the regex "," every time;
    //T(n) = 1 + 1 + n + 1 + 1 + 1 = 5 + n;
    //We use the Master Theorem, ignore the coefficients and constants and get T(n) = n.

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
            List<String> fileContent = new ArrayList<>(Files.readAllLines(Path.of(path), StandardCharsets.UTF_8));     //takes 1
            for (int i = 0; i < fileContent.size(); i++) {     //takes n to go through the size of file
                if (fileContent.get(i).equals(oldString)) {     //takes log(n) to take each element in the array and compare it
                    fileContent.set(i, newString);    //takes 1
                    break;    //takes 1
                }
            }

            Files.write(Path.of(path), fileContent, StandardCharsets.UTF_8);    //takes 1
        } catch (IOException e) {    //takes 1
            e.printStackTrace();    //takes 1
        }
    }

    // There is no recursion so we don't need a best case;
    // We loop n time through the file;
    // We execute log(n) to compare each element in the array while looping;
    // T(n) = 1 + n + log(n) + 1 + 1 + 1 + 1 + 1 + 1 = 7 + n + log(n);
    // By using Master Theorem, ignoring the constants and coeficients, we get T(n) = n+log(n);
    // We chose this method in order to be able to change data from the text file with the new one coming from the gui editing features.


    public static void deleteSelectedDataFromFile(Object selectedObject, String path) {
        File inputFile = new File(path);
        File tempFile = new File("src/main/resources/domain/myTempFile.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile)); BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            String lineToRemove = selectedObject.toString();
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

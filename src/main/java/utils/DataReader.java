package utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class DataReader {
    public ArrayList<String[]> readDataFromFile(String filename) {
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

        try {
            FileHandler.writeToBinaryFile("students.bin", arrayList);
        } catch (FileNotFoundException e) {
            System.out.println("Error opening file ");
        } catch (IOException e) {
            System.out.println("IO Error writing to file ");
        }
        return arrayList;
    }
}

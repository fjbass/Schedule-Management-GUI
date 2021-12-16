package app.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {
    public static void writeToTextFile(String fileName, String str) throws FileNotFoundException {
        writeText(fileName, str, false);
    }

    public static void appendToTextFile(String fileName, String str) throws FileNotFoundException {
        writeText(fileName, str, true);
    }

    private static void writeText(String fileName, String str, boolean append) throws FileNotFoundException {
        PrintWriter writeToFile = null;

        try {
            FileOutputStream fileOutStream = new FileOutputStream(fileName, append);
            writeToFile = new PrintWriter(fileOutStream);
            writeToFile.println(str);
        } finally {
            if (writeToFile != null) {
                writeToFile.close();
            }
        }
    }

    public static void writeArrayToTextFile(String fileName, String[] strs) throws FileNotFoundException {
        writeText(fileName, strs, false);
    }

    public static void appendArrayToTextFile(String fileName, String[] strs) throws FileNotFoundException {
        writeText(fileName, strs, true);
    }

    private static void writeText(String fileName, String[] strs, boolean append) throws FileNotFoundException {
        PrintWriter writeToFile = null;

        try {
            FileOutputStream fileOutStream = new FileOutputStream(fileName, append);
            writeToFile = new PrintWriter(fileOutStream);

            for (String str : strs) {
                writeToFile.println(str);
            }
        } finally {
            if (writeToFile != null) {
                writeToFile.close();
            }
        }
    }

    public String readFromTextFile(String fileName) throws FileNotFoundException {
        Scanner readFromFile = null;
        String str = "";

        try {
            FileInputStream fileInStream = new FileInputStream(fileName);
            readFromFile = new Scanner(fileInStream);
            str = readFromFile.nextLine();
        } finally {
            if (readFromFile != null) {
                readFromFile.close();
            }
        }
        return str;
    }

    public static String[] readArrayFromTextFile(String fileName) throws FileNotFoundException {
        Scanner readFromFile = null;
        ArrayList<String> strs = new ArrayList<>();

        try {
            FileInputStream fileInStream = new FileInputStream(fileName);
            readFromFile = new Scanner(fileInStream);

            while (readFromFile.hasNext()) {
                strs.add(readFromFile.nextLine());
            }
        } finally {
            if (readFromFile != null) {
                readFromFile.close();
            }
        }

        String[] strsArray = new String[strs.size()];
        return strs.toArray(strsArray);
    }

    public static void writeToBinaryFile(String fileName, Object obj) throws IOException {
        ObjectOutputStream writeToFile = null;

        try {
            FileOutputStream fileOutStream = new FileOutputStream(fileName);
            writeToFile = new ObjectOutputStream(fileOutStream);

            writeToFile.writeObject(obj);
        } finally {
            if (writeToFile != null) {
                try {
                    writeToFile.close();
                } catch (IOException e) {
                    System.out.println("IO Error closing file " + fileName);
                }
            }
        }
    }

    public static void writeArrayToBinaryFile(String fileName, Object[] objs) throws IOException {
        ObjectOutputStream writeToFile = null;

        try {
            FileOutputStream fileOutStream = new FileOutputStream(fileName);
            writeToFile = new ObjectOutputStream(fileOutStream);

            for (Object obj : objs) {
                writeToFile.writeObject(obj);
            }
        } finally {
            if (writeToFile != null) {
                try {
                    writeToFile.close();
                } catch (IOException e) {
                    System.out.println("IO Error closing file " + fileName);
                }
            }
        }
    }

    public static Object readFromBinaryFile(String fileName) throws IOException, ClassNotFoundException {
        Object obj = null;
        ObjectInputStream readFromFile = null;
        try {
            FileInputStream fileInStream = new FileInputStream(fileName);
            readFromFile = new ObjectInputStream(fileInStream);
            try {
                obj = readFromFile.readObject();
            } catch (EOFException eof) {
                //Done reading
            }
        } finally {
            if (readFromFile != null) {
                try {
                    readFromFile.close();
                } catch (IOException e) {
                    System.out.println("IO Error closing file " + fileName);
                }
            }
        }

        return obj;
    }

    public static Object[] readArrayFromBinaryFile(String fileName) throws IOException, ClassNotFoundException {
        ArrayList<Object> objs = new ArrayList<>();

        ObjectInputStream readFromFile = null;
        try {
            FileInputStream fileInStream = new FileInputStream(fileName);
            readFromFile = new ObjectInputStream(fileInStream);
            while (true) {
                try {
                    objs.add(readFromFile.readObject());
                } catch (EOFException eof) {
                    //Done reading
                    break;
                }
            }
        } finally {
            if (readFromFile != null) {
                try {
                    readFromFile.close();
                } catch (IOException e) {
                    System.out.println("IO Error closing file " + fileName);
                }
            }
        }

        return objs.toArray();
    }
}

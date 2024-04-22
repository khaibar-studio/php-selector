/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phpselector;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author muflih
 */
public class Data {
    private static String path = System.getProperty("user.home") + "/.phpselector/data/";

    public static void save(String name, String data) {
        String path = Data.getOrCreatePath(name);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            // Write data to the file
            writer.write(data);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static String get(String name) {
        BufferedReader reader = null;

        try {
            String data = "";
            String path = Data.getOrCreatePath(name);
            // Create a BufferedReader to read from the file
            reader = new BufferedReader(new FileReader(path));

            // Read each line from the file
            String line;
            while ((line = reader.readLine()) != null) {
                data += line;
            }
            return data;
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            // Close the BufferedReader in the finally block to ensure it's always closed
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println("Error Final: " + e.getMessage());
                }
            }
        }
        return "";
    }

    public static void log(String log) {
        String older = Data.get("log");
        older += log + "/n";
        Data.save("log", older);
    }

    private static String getOrCreatePath(String name) {
        File fileOrDirectory = new File(Data.path + name);
        if (fileOrDirectory.exists()) {
            if (fileOrDirectory.isFile()) {
                return Data.path + name;
            } else if (fileOrDirectory.isDirectory()) {
                return Data.path + name;
            }
        } else {
            File directory = new File(Data.path);
            directory.mkdirs();
        }
        return Data.path + name;
    }
}

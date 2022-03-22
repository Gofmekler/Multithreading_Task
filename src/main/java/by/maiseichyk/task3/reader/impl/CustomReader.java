package by.maiseichyk.task3.reader.impl;

import by.maiseichyk.task3.reader.CustomReaderInterface;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CustomReader implements CustomReaderInterface {

    public ArrayList<String> readFile(String filePath) {
        ArrayList<String> result = new ArrayList<>();
        try (FileReader fileReader = new FileReader(filePath);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String lineOfFile;
            while ((lineOfFile = bufferedReader.readLine()) != null) {
                result.add(lineOfFile);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}

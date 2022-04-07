package by.maiseichyk.task3.reader.impl;

import by.maiseichyk.task3.reader.CustomReaderInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CustomReader implements CustomReaderInterface {
    private static Logger logger = LogManager.getLogger();

    public ArrayList<String> readFile(String filePath) {
        ArrayList<String> result = new ArrayList<>();
        try (FileReader fileReader = new FileReader(filePath);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String lineOfFile;
            while ((lineOfFile = bufferedReader.readLine()) != null) {
                result.add(lineOfFile);
                logger.info("Line of file is successfully added to the result list");
            }
        } catch (IOException e) {
            logger.warn("IOException ", e);
        }
        return result;
    }
}

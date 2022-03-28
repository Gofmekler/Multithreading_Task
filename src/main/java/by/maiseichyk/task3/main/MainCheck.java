package by.maiseichyk.task3.main;

import by.maiseichyk.task3.entity.LogisticBase;
import by.maiseichyk.task3.entity.Terminal;
import by.maiseichyk.task3.entity.Vehicle;
import by.maiseichyk.task3.parser.impl.CustomParserImpl;
import by.maiseichyk.task3.reader.impl.CustomReader;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MainCheck {
       public static void main(String[] args) {
           String filename = "src/main/resources/vehicle.txt";
           CustomReader customReader = new CustomReader();
           CustomParserImpl customParser = new CustomParserImpl();
           ArrayList<Vehicle> listOfVehicles = new ArrayList<>();
           ArrayList<String> vehiclesToParse = customReader.readFile(filename);
           ExecutorService executor = Executors.newFixedThreadPool(5);
           for (String line : vehiclesToParse){
               listOfVehicles.add(customParser.parseVehicle(line));
           }
           for (Vehicle vehicle : listOfVehicles) {
               executor.submit(vehicle);
           }
           executor.shutdown();
    }
}
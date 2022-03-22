package by.maiseichyk.task3.entity;

import by.maiseichyk.task3.parser.impl.CustomParserImpl;
import by.maiseichyk.task3.reader.impl.CustomReader;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class LogisticBase {
    private static LogisticBase instance;
    private static ReentrantLock reentrantLock = new ReentrantLock();
    private static AtomicBoolean creator = new AtomicBoolean(false);
    private Terminal terminal;
    private Vehicle vehicle;
    private ArrayDeque<Vehicle> vehicles;
    private List<Terminal> terminals;

    public static LogisticBase getInstance(){
        if(!creator.get()){
            try{
                reentrantLock.lock();
                if(instance == null){
                    instance = new LogisticBase();
                    creator.set(true);
                }
            } finally {
                reentrantLock.unlock();
            }
        }
        return instance;
    }

    public LogisticBase(){
        CustomReader customReader = new CustomReader();
        CustomParserImpl customParser = new CustomParserImpl();
        ArrayList<String> listVehicles = customReader.readFile("/src/data.txt");
        for (String line : listVehicles){
            vehicle = customParser.parseVehicle(line);
            for(int i = 0; i < listVehicles.size(); i++){
                vehicles.add(vehicle);
            }
        }
        ArrayList<String> listOfTerminals = customReader.readFile("/src/data2.txt");
        for (String line : listOfTerminals){
            terminal = customParser.parseTerminal(line);
            for(int i = 0; i < listOfTerminals.size(); i++){
                terminals.add(terminal);
            }
        }
    }
}

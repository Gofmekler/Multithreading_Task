package by.maiseichyk.task3.entity;

import by.maiseichyk.task3.exception.CustomException;
import by.maiseichyk.task3.parser.impl.CustomParserImpl;
import by.maiseichyk.task3.reader.impl.CustomReader;

import java.sql.Time;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class LogisticBase {
    private final Semaphore semaphore = new Semaphore(3, true);
    private static LogisticBase instance;
    private static ReentrantLock reentrantLock = new ReentrantLock();
    private static AtomicBoolean creator = new AtomicBoolean(false);
    private ArrayDeque<Vehicle> vehicles;
    private ArrayDeque<Terminal> terminals;

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

    private LogisticBase(){
        CustomReader customReader = new CustomReader();
        CustomParserImpl customParser = new CustomParserImpl();
//        ArrayList<String> listVehicles = customReader.readFile("/src/data.txt");
//        for (String line : listVehicles){
//            Vehicle vehicle = customParser.parseVehicle(line);
//            for(int i = 0; i < listVehicles.size(); i++){
//                vehicles.add(vehicle);
//            }
//        }
        ArrayList<String> listOfTerminals = customReader.readFile("/src/data2.txt");
        for (String line : listOfTerminals){
            Terminal terminal = customParser.parseTerminal(line);
            for(int i = 0; i < listOfTerminals.size(); i++){
                terminals.add(terminal);
            }
        }
    }
    public Terminal getAccessToTerminal(Vehicle vehicle) throws CustomException {
        if(semaphore.tryAcquire()){
            for (Terminal terminal : terminals){
                if(!terminal.isBusy()) {
                    terminal.setIsBusy(true);//log with vehicle
                    terminals.removeFirst();
                    if (vehicle.isPriority()) {
                        vehicles.addFirst(vehicle);
                    } else {
                        vehicles.addLast(vehicle);
                    }
                    return terminal;
                }
            }
        }
        throw new CustomException();
    }

    public void releaseTerminal(Vehicle vehicle, Terminal terminal){
        terminal.setIsBusy(false);//log with vehicle
        try {
            TimeUnit.MILLISECONDS.sleep(500);
            terminals.addLast(terminal);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            semaphore.release();
        }
    }
}

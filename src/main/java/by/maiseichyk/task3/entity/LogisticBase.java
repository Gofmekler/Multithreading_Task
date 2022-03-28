package by.maiseichyk.task3.entity;

import by.maiseichyk.task3.exception.CustomException;
import by.maiseichyk.task3.parser.impl.CustomParserImpl;
import by.maiseichyk.task3.reader.impl.CustomReader;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class LogisticBase {
    private final Semaphore semaphore = new Semaphore(2, false);
    private static LogisticBase instance;
    private static ReentrantLock reentrantLock = new ReentrantLock();
    private static AtomicBoolean creator = new AtomicBoolean(false);
//    private Deque<Vehicle> vehicles = new ArrayDeque<>();
    public Deque<Terminal> terminals = new ArrayDeque<>();

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

    private LogisticBase() {
        CustomReader customReader = new CustomReader();
        CustomParserImpl customParser = new CustomParserImpl();
        ArrayList<String> listOfTerminals = customReader.readFile("src/main/resources/terminal.txt");
        for (String line : listOfTerminals){
                Terminal terminal = customParser.parseTerminal(line);
                    terminals.addLast(terminal);
                }
    }

    public Terminal getAccessToTerminal(Vehicle vehicle) throws CustomException, InterruptedException {
        Terminal terminal = null;
        try{
            semaphore.acquire();
                if (!terminals.isEmpty()){
                    //log with vehicle
                    terminal = terminals.removeFirst();
                    terminal.isBusy().set(true);
                }
            }
            catch (InterruptedException  e){
                e.printStackTrace();
            }
        return terminal;
    }

    public void releaseTerminal(Vehicle vehicle, Terminal terminal){
        //log with vehicle
        try {
            terminal.isBusy().set(false);
            TimeUnit.MILLISECONDS.sleep(50);
            terminals.addLast(terminal);
            System.out.println("Releasing terminal in Logistic base class " + Thread.currentThread().getName());
        }
        catch (InterruptedException | NullPointerException e) {
            e.printStackTrace();
        }
        finally {
            semaphore.release();
        }
    }
}

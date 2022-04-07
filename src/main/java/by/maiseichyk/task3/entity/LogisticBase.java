package by.maiseichyk.task3.entity;

import by.maiseichyk.task3.exception.CustomException;
import by.maiseichyk.task3.parser.impl.CustomParserImpl;
import by.maiseichyk.task3.reader.impl.CustomReader;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogisticBase {
    private static Logger logger = LogManager.getLogger();
    private static LogisticBase instance;
    private static ReentrantLock reentrantLock = new ReentrantLock();
    private static AtomicBoolean creator = new AtomicBoolean(false);
    private final Semaphore semaphore = new Semaphore(3, false);
    private Deque<Vehicle> vehicles = new ArrayDeque<>();
    private Deque<Terminal> terminals = new ArrayDeque<>();
    private Random random = new Random();


    public static LogisticBase getInstance() {
        if (!creator.get()) {
            try {
                reentrantLock.lock();
                if (instance == null) {
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
        for (String line : listOfTerminals) {
            Terminal terminal = customParser.parseTerminal(line);
            terminals.addLast(terminal);
            logger.info("Added a new terminal to terminals list: " + terminal);
        }
    }

    public Terminal getAccessToTerminal(Vehicle vehicle) throws CustomException, InterruptedException {
        try {
            semaphore.acquire();
            for (Terminal terminal : terminals) {
                logger.info("The vehicle got access to the terminals list: " + Thread.currentThread().getName());
                terminal.isBusy().compareAndSet(false, true);
                terminal = terminals.removeFirst();
                if (vehicle.isPriority()) {
                    vehicles.addFirst(vehicle);
                    logger.info("Vehicle" + Thread.currentThread().getName() + "has priority");
                } else {
                    vehicles.addLast(vehicle);
                }
                return terminal;
            }
        } catch (InterruptedException e) {
            logger.warn("Thread was interrupted: " + Thread.currentThread().getName());
        }
        logger.info("Vehicle can't get access to the terminal" + Thread.currentThread().getName());
        throw new CustomException("Can't get access to the terminal");
    }

    public void releaseTerminal(Terminal terminal) {
        try {
            terminal.isBusy().set(false);
            TimeUnit.MILLISECONDS.sleep(random.nextInt(5, 500));
            terminals.addLast(terminal);
            logger.info("Releasing terminal in Logistic base class " + Thread.currentThread().getName());
        } catch (InterruptedException | NullPointerException e) {
            logger.warn("Current thread can't release terminal: " + Thread.currentThread().getName());
        } finally {
            logger.info("Terminal released");
            semaphore.release();
        }
    }
}

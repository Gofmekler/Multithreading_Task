package by.maiseichyk.task3.entity;

import by.maiseichyk.task3.exception.CustomException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class Terminal {
    private static Logger logger = LogManager.getLogger();
    private int terminalId;
    private int terminalCapacity;
    private AtomicBoolean isBusy = new AtomicBoolean(false);
    private Random random = new Random();

    public Terminal(int terminalId, int terminalCapacity) {
        this.terminalId = terminalId;
        this.terminalCapacity = terminalCapacity;
    }

    public AtomicBoolean isBusy() {
        return isBusy;
    }

    public int getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(int terminalId) {
        this.terminalId = terminalId;
    }

    public int getTerminalCapacity() {
        return terminalCapacity;
    }

    public void setTerminalCapacity(int terminalCapacity) {
        this.terminalCapacity = terminalCapacity;
    }

    public void useVehicle(Vehicle vehicle) {
        try {
            TimeUnit.MILLISECONDS.sleep(random.nextInt(5, 500));
            if(vehicle.upLoad()){
                if(terminalCapacity > vehicle.getVehicleCapacity()){
                    terminalCapacity -= vehicle.getVehicleCapacity();
                }
                else{
                    logger.warn("Vehicle can't be uploaded due to low terminal's capacity");
                    throw new CustomException("Vehicle can't be uploaded due to low terminal's capacity");
                }
            }
            else {
                terminalCapacity += vehicle.getVehicleCapacity();
            }
            logger.info("Using terminal by vehicle " + terminalCapacity + " " + Thread.currentThread().getName());
        } catch (InterruptedException | CustomException e) {
            logger.warn("Current thread was interrupted: " + Thread.currentThread().getName());
            Thread.currentThread().interrupt();
        }
    }
}

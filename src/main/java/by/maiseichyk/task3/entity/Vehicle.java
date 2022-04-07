package by.maiseichyk.task3.entity;

import by.maiseichyk.task3.exception.CustomException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Vehicle extends Thread {
    private static Logger logger = LogManager.getLogger();
    private int vehicleId;
    private boolean upLoad;
    private boolean isPriority;
    private int vehicleCapacity ;

    public Vehicle(int vehicleId, int vehicleCapacity, boolean isPriority, boolean upLoad){
        this.vehicleId = vehicleId;
        this.vehicleCapacity = vehicleCapacity;
        this.isPriority = isPriority;
        this.upLoad = upLoad;
        }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public boolean upLoad() {
        return upLoad;
    }

    public void setLoaded(boolean loaded) {
        upLoad = loaded;
    }

    public boolean isPriority() {
        return isPriority;
    }

    public void setPriority(boolean priority) {
        isPriority = priority;
    }

    public int getVehicleCapacity() {
        return vehicleCapacity;
    }

    public void setVehicleCapacity(int vehicleCapacity) {
        this.vehicleCapacity = vehicleCapacity;
    }

    @Override
    public void run() {
        logger.info("Thread starts: " + Thread.currentThread().getName());
        LogisticBase logisticBase = LogisticBase.getInstance();
        Terminal terminal = null;
        try{
            logger.info("Thread: " + Thread.currentThread().getName() + " is tying to get access to terminal");
            terminal = logisticBase.getAccessToTerminal(this);
            terminal.useVehicle(this);
        } catch (CustomException | InterruptedException e) {
            logger.warn("Thread was interrupted: " + Thread.currentThread().getName());
        }
        finally {
            logisticBase.releaseTerminal(terminal);
            logger.info("Releasing terminal");
        }
    }
}
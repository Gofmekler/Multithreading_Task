package by.maiseichyk.task3.entity;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class Terminal {
    private int terminalId;
    private int terminalCapacity;
    private AtomicBoolean isBusy = new AtomicBoolean(false);

    public Terminal(int terminalId, int terminalCapacity) {
        this.terminalId = terminalId;
        this.terminalCapacity = terminalCapacity;
    }

    public AtomicBoolean isBusy(){
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

    public void useVehicle(Vehicle vehicle){
        try {
            TimeUnit.MILLISECONDS.sleep(5);
            terminalCapacity = vehicle.upLoad() ? terminalCapacity - vehicle.getVehicleFullness() : terminalCapacity + vehicle.getVehicleFullness();
            System.out.println("Using terminal by vehicle " + terminalCapacity + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace(); //log with terminal info id
            Thread.currentThread().interrupt();
        }
    }
}

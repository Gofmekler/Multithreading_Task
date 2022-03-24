package by.maiseichyk.task3.entity;

import by.maiseichyk.task3.parser.impl.CustomParserImpl;

import java.util.concurrent.TimeUnit;

public class Terminal {
    private int terminalId;
    private int terminalCapacity;
    private boolean isBusy;

    public Terminal(int terminalId, int terminalCapacity){
        this.terminalId = terminalId;
        this.terminalCapacity = terminalCapacity;
    }

    public boolean isBusy(){
        return isBusy;
    }

    public void setIsBusy(boolean isBusy){
        this.isBusy = isBusy;
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
            TimeUnit.SECONDS.sleep(5);
            terminalCapacity = vehicle.upLoad() ? terminalCapacity - vehicle.getVehicleFullness() : terminalCapacity + vehicle.getVehicleFullness();
        } catch (InterruptedException e) {
            e.printStackTrace(); //log with terminal info id
            Thread.currentThread().interrupt();
        }

    }
}

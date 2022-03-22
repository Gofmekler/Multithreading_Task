package by.maiseichyk.task3.entity;

import by.maiseichyk.task3.parser.impl.CustomParserImpl;

import java.util.concurrent.TimeUnit;

public class Terminal {
    private int terminalId;
    private int terminalCapacity;

    public Terminal(int terminalId, int terminalCapacity){
        this.terminalId = terminalId;
        this.terminalCapacity = terminalCapacity;
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
    public void releaseVehicle(Vehicle vehicle){
        try {
            TimeUnit.SECONDS.sleep(5);
            CustomParserImpl parser = new CustomParserImpl();
            parser.parseTerminal("21231231");
            terminalCapacity = vehicle.upLoad() ? terminalCapacity - vehicle.getVehicleFullness() : terminalCapacity + vehicle.getVehicleFullness();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

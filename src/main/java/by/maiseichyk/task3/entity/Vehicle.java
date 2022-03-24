package by.maiseichyk.task3.entity;

import by.maiseichyk.task3.exception.CustomException;

public class Vehicle implements Runnable {
    private int vehicleId;
    private boolean upLoad;
    private boolean isPriority;
    private int vehicleFullness ;

    public Vehicle(int vehicleId, int vehicleFullness, boolean isPriority, boolean upLoad){
        this.vehicleId = vehicleId;
        this.vehicleFullness = vehicleFullness;
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

    public int getVehicleFullness() {
        return vehicleFullness;
    }

    public void setVehicleFullness(int vehicleFullness) {
        this.vehicleFullness = vehicleFullness;
    }

    @Override
    public void run() {
        LogisticBase logisticBase = LogisticBase.getInstance();
        Terminal terminal = null;
        try{
            terminal = logisticBase.getAccessToTerminal(this);
            terminal.useVehicle(this);
        } catch (CustomException e) {
            e.printStackTrace();
        }
        finally {
            logisticBase.releaseTerminal(this, terminal);
        }
    }
}
package by.maiseichyk.task3.entity;

import java.util.concurrent.Callable;

public class Vehicle implements Callable<String> {
    private int vehicleId;
    private boolean upLoad;
    private boolean isPriority;
    private int vehicleCapacity ;
    private int vehicleFullness ;

    public Vehicle(int vehicleId, int vehicleCapacity, int vehicleFullness, boolean isPriority, boolean upLoad){
        this.vehicleId = vehicleId;
        this.vehicleCapacity = vehicleCapacity;
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

    public int getVehicleCapacity() {
        return vehicleCapacity;
    }

    public void setVehicleCapacity(int vehicleCapacity) {
        this.vehicleCapacity = vehicleCapacity;
    }

    public int getVehicleFullness() {
        return vehicleFullness;
    }

    public void setVehicleFullness(int vehicleFullness) {
        this.vehicleFullness = vehicleFullness;
    }

    @Override
    public String call() throws Exception {
        return null;
    }
}
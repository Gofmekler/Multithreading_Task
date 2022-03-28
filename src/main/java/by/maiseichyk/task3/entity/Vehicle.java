package by.maiseichyk.task3.entity;

import by.maiseichyk.task3.exception.CustomException;

public class Vehicle extends Thread {
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
        System.out.println("Thread starts " + Thread.currentThread().getName());
        LogisticBase logisticBase = LogisticBase.getInstance();
        System.out.println("get instance " + Thread.currentThread().getName());
        Terminal terminal = null;
        try{
            System.out.println("getting access..." + Thread.currentThread().getName());
            terminal = logisticBase.getAccessToTerminal(this);
            System.out.println("Try to use terminal.... " + Thread.currentThread().getName());
            terminal.useVehicle(this);
        } catch (CustomException | InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            logisticBase.releaseTerminal(this, terminal);
            System.out.println("Releasing terminal in vehicle class " + Thread.currentThread().getName());
        }
    }
}
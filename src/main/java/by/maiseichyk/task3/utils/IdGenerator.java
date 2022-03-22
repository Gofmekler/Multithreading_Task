package by.maiseichyk.task3.utils;

public class IdGenerator {

    private static int currentId = 0;
    private IdGenerator(){
    }

    public static int generateId(){
        return ++currentId;
    }
}

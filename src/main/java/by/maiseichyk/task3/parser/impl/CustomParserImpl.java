package by.maiseichyk.task3.parser.impl;

import by.maiseichyk.task3.entity.Terminal;
import by.maiseichyk.task3.entity.Vehicle;
import by.maiseichyk.task3.parser.CustomParserInterface;

public class CustomParserImpl implements CustomParserInterface {
    private static final String SPLIT_REGEX = "\\s+";
//    private final int TERMINAL_CAPACITY = 15000;

    @Override
    public Vehicle parseVehicle(String lineVehicle) {
        String[] values = lineVehicle.split(SPLIT_REGEX);
        int id = Integer.parseInt(values[0]);
        int vehicleFullness = Integer.parseInt(values[1]);
        boolean priority = Boolean.parseBoolean(values[2]);
        boolean upLoad = Boolean.parseBoolean(values[3]);
        return new Vehicle(id, vehicleFullness, priority, upLoad);
    }

    @Override
    public Terminal parseTerminal(String lineTerminal) {
        String[] values = lineTerminal.split(SPLIT_REGEX);
        int id = Integer.parseInt(values[0]);
        int terminalCapacity = Integer.parseInt(values[1]);
        return new Terminal(id, terminalCapacity);
    }
}

package by.maiseichyk.task3.parser.impl;

import by.maiseichyk.task3.entity.Terminal;
import by.maiseichyk.task3.entity.Vehicle;
import by.maiseichyk.task3.parser.CustomParserInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CustomParserImpl implements CustomParserInterface {
    private static final String SPLIT_REGEX = "\\s+";
    private static Logger logger = LogManager.getLogger();

    @Override
    public Vehicle parseVehicle(String lineVehicle) {
        String[] values = lineVehicle.split(SPLIT_REGEX);
        int id = Integer.parseInt(values[0]);
        int vehicleFullness = Integer.parseInt(values[1]);
        boolean priority = Boolean.parseBoolean(values[2]);
        boolean upLoad = Boolean.parseBoolean(values[3]);
        logger.info("Created a new vehicle object: " + lineVehicle);
        return new Vehicle(id, vehicleFullness, priority, upLoad);
    }

    @Override
    public Terminal parseTerminal(String lineTerminal) {
        String[] values = lineTerminal.split(SPLIT_REGEX);
        int id = Integer.parseInt(values[0]);
        int terminalCapacity = Integer.parseInt(values[1]);
        logger.info("Created a new terminal object: " + lineTerminal);
        return new Terminal(id, terminalCapacity);
    }
}

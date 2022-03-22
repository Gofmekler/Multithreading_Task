package by.maiseichyk.task3.parser;

import by.maiseichyk.task3.entity.Terminal;
import by.maiseichyk.task3.entity.Vehicle;

public interface CustomParserInterface {

    Vehicle parseVehicle(String lineOfFile);

    Terminal parseTerminal(String lineOfFile);

}

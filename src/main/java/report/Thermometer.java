package report;

import java.util.concurrent.ThreadLocalRandom;

public class Thermometer {

    public Temperature getCurrentTemperature() {
        double temperature = ThreadLocalRandom.current().nextDouble(-40, 50);
        return new Temperature(truncateToOneDecimalPlace(temperature));
    }

    private double truncateToOneDecimalPlace(double temperature) {
        return ((int) (temperature * 10)) / 10.0;
    }
}

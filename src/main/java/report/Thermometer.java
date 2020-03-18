package report;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import quoter.TerminatorQuoter;

import java.util.concurrent.ThreadLocalRandom;

@Component("thermometer")
public class Thermometer {

    public Thermometer() {
//        System.out.println("Creating thermometer");
    }

    @Scope("prototype")
    @Bean(name = "second", initMethod = "sayQuote", destroyMethod = "destroy")
    public TerminatorQuoter terminatorQuoter() {
        TerminatorQuoter quoter = new TerminatorQuoter();
        quoter.setMessage("I'll be back!!");
        return quoter;
    }

    public Temperature getCurrentTemperature() {
        double temperature = ThreadLocalRandom.current().nextDouble(-40, 50);
        return new Temperature(truncateToOneDecimalPlace(temperature));
    }

    private double truncateToOneDecimalPlace(double temperature) {
        return ((int) (temperature * 10)) / 10.0;
    }
}

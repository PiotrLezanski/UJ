import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main {
    static final double HUMIDITY = 424.42;
    static final double TEMPERATURE = 13.53;
    static final double PRESSURE = 1020;

    public static void main(String[] args) {
        CurrentConditions currentConditions = new CurrentConditions();
        StatisticsDisplay statisticsDisplay = new StatisticsDisplay();
        ForecastDisplay forecastDisplay = new ForecastDisplay();

        WeatherData weatherData = new WeatherData(HUMIDITY, TEMPERATURE, PRESSURE);
        weatherData.registerObserver(currentConditions);
        weatherData.registerObserver(statisticsDisplay);
        weatherData.registerObserver(forecastDisplay);

        weatherData.setMeasurements(135.53, 26.27, 2636);
    }
}
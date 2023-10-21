import java.util.ArrayList;

public class WeatherData implements Subject
{
    private double humidity;
    private double temperature;
    private double pressure;
    ArrayList<Observer> observers;

    public WeatherData(double humidity, double temperature, double pressure) {
        this.humidity = humidity;
        this.temperature = temperature;
        this.pressure = pressure;
        observers = new ArrayList<>();
    }

    public double getHumidity()
    {
        return humidity;
    }

    public double getTemperature()
    {
        return temperature;
    }

    public double getPressure()
    {
        return pressure;
    }

    public void setMeasurements(double hum, double temp, double pres)
    {
        humidity = hum;
        temperature = temp;
        pressure = pres;
        notifyObservers();
    }

    @Override
    public void notifyObservers()
    {
        for(Observer observer : observers)
        {
            observer.update(humidity, temperature, pressure);
        }
    }

    @Override
    public void registerObserver(Observer observer)
    {
        observers.add(observer);
    }
}

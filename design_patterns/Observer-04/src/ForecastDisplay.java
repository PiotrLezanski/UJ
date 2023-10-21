public class ForecastDisplay implements Observer
{
    public ForecastDisplay() {}

    @Override
    public void update(double humidity, double temperature, double pressure)
    {
        displayPredictions();
    }

    void displayPredictions()
    {
        System.out.println("ForecastDisplay updated");
    }
}

public class StatisticsDisplay implements Observer
{
    public StatisticsDisplay() {}

    @Override
    public void update(double humidity, double temperature, double pressure)
    {
        displayStats();
    }

    void displayStats()
    {
        System.out.println("StatisticsDisplay updated");
    }
}

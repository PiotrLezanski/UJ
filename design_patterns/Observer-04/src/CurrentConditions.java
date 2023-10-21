public class CurrentConditions implements Observer
{
    public CurrentConditions() {}

    @Override
    public void update(double humidity, double temperature, double pressure)
    {
        display();
    }

    void display()
    {
        System.out.println("CurrentConditions updated");
    }
}

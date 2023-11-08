import java.awt.*;

abstract public class PaintStation
{
    public void releaseVehicle()
    {
        System.out.println("The vehicle is released");
    }

    abstract public void paintWork();
    abstract public void polishWork();
    abstract public void removeDentsWork();
}

class CarPaint extends PaintStation
{
    @Override
    public void paintWork()
    {
        System.out.println("The car is painted");
    }

    @Override
    public void polishWork()
    {
        System.out.println("The car is polished");
    }

    @Override
    public void removeDentsWork()
    {
        System.out.println("All dents are removed from the car");
    }
}

public class Vehicle
{
    LegacyCar legacyCar;

    Vehicle(double x, double y)
    {
        legacyCar = new LegacyCar(x, y);
    }

    void moveTo(double move_x, double move_y)
    {
        legacyCar.drive(move_x-legacyCar.getX(), move_y-legacyCar.getY());
    }
}

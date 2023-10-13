public class LegacyCar
{
    private double x;
    private double y;

    LegacyCar(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public void drive(double move_x, double move_y)
    {
        x += move_x;
        y += move_y;
    }

    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }
}

abstract public class Vehicle
{
    protected PaintStation paintStation;
    public Vehicle(PaintStation paintStation)
    {
        this.paintStation = paintStation;
    }

    public void release()
    {
        paintStation.releaseVehicle();
    }

    abstract public void paint();
    abstract public void polish();
    abstract public void removeDents();
}

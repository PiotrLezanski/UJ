abstract public class Vehicle
{
    protected PaintStation paintStation;
    public Vehicle(PaintStation paintStation)
    {
        this.paintStation = paintStation;
    }

    abstract public void paint();
    abstract public void polish();
    abstract public void removeDents();
}

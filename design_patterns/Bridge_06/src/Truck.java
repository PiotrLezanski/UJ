class Truck extends Vehicle
{
    public Truck(PaintStation paintStation)
    {
        super(paintStation);
    }

    @Override
    public void paint()
    {
        System.out.println("Truck is painted");
        paintStation.paintWork();
    }

    @Override
    public void polish()
    {
        System.out.println("Truck is polished");
        paintStation.polishWork();
    }

    @Override
    public void removeDents()
    {
        System.out.println("Truck is dent-free");
        paintStation.removeDentsWork();
    }
}
public class Limousine extends Vehicle
{
    public Limousine(PaintStation paintStation)
    {
        super(paintStation);
    }

    @Override
    public void paint()
    {
        System.out.println("Limousine is painted");
        paintStation.paintWork();
    }

    @Override
    public void polish()
    {
        System.out.println("Limousine is polished");
        paintStation.polishWork();
    }

    @Override
    public void removeDents()
    {
        System.out.println("Limousine is dent-free");
        paintStation.removeDentsWork();
    }
}


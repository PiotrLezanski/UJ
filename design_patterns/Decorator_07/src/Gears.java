public class Gears extends BikeDecorator
{
    public Gears(BikeInterface bike)
    {
        super(bike);
    }

    @Override
    public String decorate()
    {
        return super.decorate() + decorateWithGears();
    }

    private String decorateWithGears()
    {
        return " with gears";
    }
}

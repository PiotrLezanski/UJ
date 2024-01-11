public class SteeringWheel extends BikeDecorator
{
    public SteeringWheel(BikeInterface bike)
    {
        super(bike);
    }

    @Override
    public String decorate()
    {
        return super.decorate() + decorateWithSteeringWheel();
    }

    private String decorateWithSteeringWheel()
    {
        return " with steering wheel";
    }
}

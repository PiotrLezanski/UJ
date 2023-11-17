public class BikeDecorator implements BikeInterface
{
    private BikeInterface bike;

    public BikeDecorator(BikeInterface bike) {
        this.bike = bike;
    }

    @Override
    public String decorate()
    {
        return bike.decorate();
    }
}

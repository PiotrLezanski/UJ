
public class Main
{
    public static void main(String[] args)
    {
        Vehicle vehicle1 = new Truck(new CarPaint());
        vehicle1.paint();
        vehicle1.polish();
        vehicle1.removeDents();

        Vehicle vehicle2 = new Truck(new CarPaint());
        vehicle2.paint();
        vehicle2.polish();
        vehicle2.removeDents();

        vehicle1.release();
        vehicle2.release();
    }
}
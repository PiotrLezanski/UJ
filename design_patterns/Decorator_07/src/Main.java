public class Main {
    public static void main(String[] args) {
        BikeInterface bike = new BikeImpl();

        bike = new SteeringWheel(bike);
        bike = new Gears(bike);
    }
}
import java.util.ArrayList;

public class Racetrack
{
    ArrayList<Vehicle> vehicles;

    Racetrack()
    {
        vehicles = new ArrayList<>();
    }

    void addVehicle(Vehicle vehicle)
    {
        vehicles.add(vehicle);
    }
}

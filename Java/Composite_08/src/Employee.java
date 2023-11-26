public class Employee implements IUniversityPart 
{
    String id;
    String name;
    int hoursWorked = 0;

    public Employee(String id, String name, int hoursWorked) {
        this.id = id;
        this.name = name;
        this.hoursWorked = hoursWorked;
    }

    @Override
    public void printName() {
        System.out.println("The employee name is: " + name);
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public int getHoursWorked() {
        return hoursWorked;
    }
    
    void addHoursWorked(int hours)
    {
        hoursWorked += hours;
    }
}

import java.util.ArrayList;

public class Department implements IUniversityPart
{
    String id;
    String name;

    ArrayList<IUniversityPart> children = new ArrayList<>();
    
    public Department(String id, String name) {
        this.id = id;
        this.name = name;
    }
    
    @Override
    public void printName() {
        System.out.println("The department name is: " + name);
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public int getHoursWorked() {
        int hours = 0;
        for(IUniversityPart universityPart : children)
            hours += universityPart.getHoursWorked();
        return hours;
    }

    void add(IUniversityPart universityPart)
    {
        children.add(universityPart);
    }

    void remove(IUniversityPart universityPart)
    {
        children.remove(universityPart);
    }

    IUniversityPart get(int index)
    {
        return children.get(index);
    }
}

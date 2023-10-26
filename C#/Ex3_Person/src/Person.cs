using System.IO.Compression;

public class Person
{
    public string Name;
    public string Surname;

    public Person(string name, string surname)
    {
        Name = name;
        Surname = surname;
    }

    public void printData()
    {
        Console.WriteLine(Name + " " + Surname);
    }
}
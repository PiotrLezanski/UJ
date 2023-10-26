
namespace person_program
{
    public class Program
    {
        public static void Main(string[] args)
        {
            Person person = new("John", "Nowak");
            person.printData();

            ChangeNameAndSurname_substitution(person);
            person.printData();

            ChangeNameAndSurname_newObject(ref person);
            person.printData();
        }

        public static void ChangeNameAndSurname_substitution(Person person)
        {
            person.Name = "Tom";
            person.Surname = "Something";
        }

        public static void ChangeNameAndSurname_newObject(ref Person person)
        {
            person = new("Rob", "Nothing");
        }
    }
}

public class Main
{
    public static void main(String[] args) {
        Employee empl1 = new Employee("1", "Tom", 3);
        Employee empl2 = new Employee("2", "Jack", 7);
        Employee empl3 = new Employee("3", "Lizzy", 3);
        Employee empl4 = new Employee("4", "Jack", 4);
        Employee empl5 = new Employee("5", "Jus", 13);
        Employee empl6 = new Employee("6", "Mike", 9);
        Employee empl7 = new Employee("7", "Jessy", 11);
        Employee empl8 = new Employee("8", "Steve", 20);

        Faculty CSFaculty = new Faculty("WMII", "Faculty of Computer Science");

        Chair algebraChair = new Chair("A1", "Algebra Chair");
        Chair mathChair = new Chair("A2", "Math Chair");

        Institute probabilityInstitute = new Institute("I1", "Probability Institute");
        Institute mathAnalysisInstitute = new Institute("I2", "Math Analysis Institute");

        Department computerNetworksDepartment = new Department("D1", "Computer Networks Department");
        Department statisticsDepartment = new Department("D2", "Statistics Department");
        
        CSFaculty.add(mathChair);
        CSFaculty.add(computerNetworksDepartment);
        CSFaculty.add(empl5);
        
        probabilityInstitute.add(statisticsDepartment);
        probabilityInstitute.add(empl1);
        statisticsDepartment.add(empl2);
        
        mathChair.add(mathAnalysisInstitute);
        mathChair.add(empl3);
        mathChair.add(algebraChair);
        mathChair.add(empl4);
        
        computerNetworksDepartment.add(empl6);
        computerNetworksDepartment.add(probabilityInstitute);
        computerNetworksDepartment.add(empl7);
        
        algebraChair.add(empl8);
        
        System.out.println(CSFaculty.getHoursWorked());
    }
}
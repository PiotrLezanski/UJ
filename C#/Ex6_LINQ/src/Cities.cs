using System.Reflection.PortableExecutable;
using System.Security.Authentication;

namespace Cities
{
    class Cities
    {
        public static void Main(String[] args)
        {
            string? input;
            List<string> cities = new List<string>();
            while((input = Console.ReadLine()) != "X")
            {
                if(input != null)
                    cities.Add(input);
            }

            var groupedCities = cities
                                .GroupBy(x => x[0])
                                .OrderBy(x => x)
                                .ToDictionary(x => x.Key, x => x);

            while(!String.IsNullOrEmpty(input = Console.ReadLine()))
            {
                char ch = input[0];
                Console.WriteLine(groupedCities.ContainsKey(ch) ? String.Join(", ", groupedCities[ch]) : "PUSTE");
            }
        }
    }
}
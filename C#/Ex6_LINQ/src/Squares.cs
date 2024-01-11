namespace Linq_squares
{
    class Squares
    {
        public static void Main(String[] args)
        {
            Console.Write("N: ");
            int n = Convert.ToInt32(Console.ReadLine());

            var sq = Enumerable.Range(1, n)
                    .Where(num => num != 5 && num != 9 && (num%2 == 1 || num%7 == 0) )
                    .Select(num => num * num);

            Console.Write($"Sum of all elements: {sq.Sum()}");
            Console.Write($"Number of all elements: {sq.Count()}");
            Console.Write($"First element: {sq.First()}");
            Console.Write($"Last element: {sq.Last()}");
            var third = sq.Count() < 3 ? "null" : sq.ElementAt(2).ToString();
            Console.Write($"Third element: {third}");
        }   
    }
}
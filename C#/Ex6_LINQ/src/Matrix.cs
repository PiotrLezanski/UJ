namespace MatrixSum
{
    class Matrix
    {
        public static void Main(String[] args)
        {
            Console.Write("N: ");
            int n = Convert.ToInt32(Console.ReadLine());
            Console.Write("M: ");
            int m = Convert.ToInt32(Console.ReadLine());

            Random rand = new Random();

            var seq = Enumerable.Range(1, n)
                    .Select(x => Enumerable.Range(1, m)
                                .Select(_ => rand.Next(1000)))
                    .ToList();

            List<List<int>> matrix = Enumerable.Range(1, n)
                                    .Select(x => )
        }
    }
}
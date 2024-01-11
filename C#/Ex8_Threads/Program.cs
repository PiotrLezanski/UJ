using System.Diagnostics;
using System.Net.NetworkInformation;

namespace Threads_exercise
{
    class Program
    {
        private static readonly string filePath = "data.txt";

        public static void Main(string[] args)
        {
            var map = ParseFileToMap(filePath);

            Console.WriteLine("====== Sequential ======");
            PrintExecutionTime(() => { PingSequentially(map); }, "Sequential");
            Console.WriteLine("========================");
            Console.WriteLine();

            Console.WriteLine("====== AsParallel ======");
            PrintExecutionTime(() => { PingAsParallel(map); }, "AsParallel");
            Console.WriteLine("========================");
            Console.WriteLine();

            Console.WriteLine("====== With Tasks ======");
            PrintExecutionTime(() => { PingWithTasks(map); }, "With Tasks");
            Console.WriteLine("========================");
            Console.WriteLine();
        }

        private static void PingWithTasks(Dictionary<string, List<string>> map)
        {
            var tasks = new List<Task>();

            foreach (var entry in map)
            {
                foreach (var url in entry.Value)
                {
                    var task = Task.Run(() => PingServer(url));
                    tasks.Add(task);
                }
            }

            Task.WaitAll(tasks.ToArray());
        }

        private static void PingAsParallel(Dictionary<string, List<string>> map)
        {
            map.AsParallel()
                .ForAll(entry => {
                    foreach(var url in entry.Value)
                    {
                        PingServer(url);
                    }
                });
        }

        private static void PingSequentially(Dictionary<string, List<string>> map)
        {
            if(map == null)
                return;

            foreach(var entry in map)
            {
                foreach(var url in entry.Value)
                {
                    PingServer(url);
                }
            }
        }

        private static void PingServer(string url)
        {
            Ping pingSender = new Ping();
            try
            {
                PingReply pingReply = pingSender.Send(url);

                if(pingReply.Status != IPStatus.Success)
                {
                    Console.WriteLine($"FAILED url: {url}, Status: {pingReply.Status}");
                }
            }
            catch (PingException ex)
            {
                Console.WriteLine($"ERROR url: {url}, Message: {ex.Message}");
            }
            catch (Exception ex)
            {
                Console.WriteLine($"ERROR url: {url}, Message: {ex.Message}");
            }
        }

        private static Dictionary<string, List<string>> ParseFileToMap(string filePath)
        {
            var map = new Dictionary<string, List<string>>();

            var lines = File.ReadLines(filePath).Skip(1);
            if(lines == null)
                return map;

            foreach(var line in lines)
            {
                string[] CountryAddress = line.Split(";");
                if(CountryAddress.Length != 2)
                    continue;

                if(!map.ContainsKey(CountryAddress[0]))
                {
                    map[CountryAddress[0]] = new List<string>();
                }
                map[CountryAddress[0]].Add(CountryAddress[1]);
            }

            return map;
        }

        public static void PrintExecutionTime(Action method, string name)
        {
            var stopWatch = Stopwatch.StartNew();
            method.Invoke();
            stopWatch.Stop();

            Console.WriteLine($"{name} method execution time: {stopWatch.ElapsedMilliseconds} ms");
        }
    }
}
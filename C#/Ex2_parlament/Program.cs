using System;

class Program
{
    public static void Main(String[] args)
    {
        Console.Write("Numer of parliamentarians: ");
        var n = Convert.ToInt32(Console.ReadLine());

        Parliament parliament = new Parliament(n);
        string topic = null;
        while (true)
        {
            Console.Write("> ");
            var cmd = Console.ReadLine().Trim().Split();
            switch (cmd[0].ToLower())
            {
                case "begin":
                    if (topic != null)
                    {
                        Console.Error.WriteLine("You cannot start new voting in the previous one!");
                        break;
                    }

                    topic = string.Join(' ', cmd.Skip(1).ToArray());
                    parliament.StartVoting(topic);
                    break;

                case "vote":
                    if (cmd[1].ToLower() == "all")
                    {
                        foreach (var p in parliament.Parliamentarians)
                        {
                            p.Vote();
                        }
                    }
                    else
                    {
                        var id = Convert.ToInt32(cmd[1]);
                        parliament.Parliamentarians[id].Vote();
                    }
                    break;

                case "end":
                    if (topic == null)
                    {
                        Console.Error.WriteLine("No voting takes place right now!");
                        break;
                    }

                    parliament.StopVoting();
                    parliament.PrintResults(topic);
                    topic = null;
                    break;

                case "q":
                case "quit":
                    return;

                default:
                    Console.Error.WriteLine("No such operation");
                    break;
            }
        }
    }
}

using System.Net;
using System.Runtime.CompilerServices;

public delegate void BeginVote(string topic);
public delegate void EndVote();
public delegate void Voted(bool vote); // 0 - against, 1 - in favour

class Parliament
{
    public List<Parlamentarian> Parliamentarians { get; }

    public event BeginVote beginVote;
    public event EndVote endVote;

    private uint VotesInFavour;
    private uint VotesAgainst;

    public Parliament(int n)
    {
        Parliamentarians = new List<Parlamentarian>();
        for(uint i=0; i<n; ++i)
        {
            Parliamentarians.Add(new Parlamentarian(i, this));
        }
    }

    public void StartVoting(string topic)
    {
        VotesInFavour = 0;
        VotesAgainst = 0;
        beginVote?.Invoke(topic);
    }

    public void StopVoting()
    {
        endVote?.Invoke();
    }

    public void CountVote(bool isInFavour)
    {
        if(isInFavour)
            ++VotesInFavour;
        else
            ++VotesAgainst;
    }

    public void PrintResults(string topic)
    {
        var withdrawn = Parliamentarians.Count - VotesInFavour - VotesAgainst;
        Console.WriteLine($"Voting topic: {topic}");
        Console.WriteLine($"  In favor:   {VotesInFavour}");
        Console.WriteLine($"  Against:    {VotesAgainst}");
        Console.WriteLine($"  Withdrawn:  {withdrawn}");
    }
}

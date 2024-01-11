class Parlamentarian
{
    public uint Id { get; set; }
    public string Topic { get; set; }
    public bool AlreadyVoted { get; set; }

    public event Voted voted;

    public Parlamentarian(uint id, Parliament parliament)
    {
        Id = id;
        Topic = null;
        AlreadyVoted = false;
        parliament.beginVote += StartThinking;
        parliament.endVote += StopThinking;
        voted += parliament.CountVote;
    }

    void StartThinking(string topic)
    {
        Topic = topic;
    }

    void StopThinking()
    {
        Topic = null;
    }

    public void Vote()
    {
        if(Topic == null)
        {
            Console.WriteLine($"Parlamentarian {Id} is not taking part in voting");
            return;
        }

        if(AlreadyVoted)
        {
            Console.WriteLine($"Parlamentarian {Id} already voted");
            return;
        }

        voted?.Invoke(new Random().Next(2) == 1);
        AlreadyVoted = true;
    }
}
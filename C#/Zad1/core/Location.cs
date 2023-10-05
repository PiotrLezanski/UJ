class Location
{
    public string Name { get; set; }
    public List<NonPlayerCharacter> NPCList { get; set; }

    public Location(string name, List<NonPlayerCharacter> list)
    {
        this.Name = name;
        this.NPCList = list;
    }
}
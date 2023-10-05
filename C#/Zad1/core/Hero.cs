enum EHeroClass
{
    Barbarian = 1,
    Paladin,
    Amazon
}

class Hero
{
    public string Name { get; set; }

    public EHeroClass HeroClass { get; set; }

    public Hero(string name, EHeroClass heroClass)
    {
        this.Name = name;
        this.HeroClass = heroClass;
    }
}
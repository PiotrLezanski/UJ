enum EHeroClass
{
    Barbarian = 1,
    Paladin,
    Amazon
}

class Hero
{
    public string name {get; set; }
    public EHeroClass heroClass {get; set;}

    public Hero(string name, EHeroClass heroClass)
    {
        this.name = name;
        this.heroClass = heroClass;
    }
}
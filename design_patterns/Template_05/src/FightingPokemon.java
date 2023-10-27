public class FightingPokemon extends Pokemon
{
    public FightingPokemon(String name, int power, int attack, int defense)
    {
        super(name, power, attack, defense);
    }

    @Override
    public double calcImpact()
    {
        return power*1.1;
    }
}

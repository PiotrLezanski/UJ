public class PoisonPokemon extends Pokemon
{
    public PoisonPokemon(String name, int power, int attack, int defense)
    {
        super(name, power, attack, defense);
    }

    @Override
    public double calcImpact()
    {
        return power*2.5;
    }
}

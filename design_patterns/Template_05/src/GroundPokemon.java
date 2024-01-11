public class GroundPokemon extends Pokemon
{
    public GroundPokemon(String name, int power, int attack, int defense)
    {
        super(name, power, attack, defense);
    }

    @Override
    public double calcImpact()
    {
        return power*0.5;
    }
}

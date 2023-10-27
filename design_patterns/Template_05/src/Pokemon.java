public abstract class Pokemon {
    String name;
    int power;
    int attack;
    int defense;

    public Pokemon(String name, int power, int attack, int defense) {
        this.name = name;
        this.power = power;
        this.attack = attack;
        this.defense = defense;
    }

    public abstract double calcImpact();

    public double calcDamage() {
        return calcImpact() * 1.5;
    }
}
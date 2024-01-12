public abstract class AutoPart 
{
    abstract void accept(Visitor visitor);

    abstract int fabricationCost();
    abstract int fabricationTime();
    abstract int avgUsageTime();
    abstract int yearlyCost();
    
    abstract void fix(String a);
}

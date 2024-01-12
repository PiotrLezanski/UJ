class BodyPart extends AutoPart 
{
    @Override
    void accept(Visitor visitor) 
    {
        visitor.visit(this);
    }

    @Override
    int fabricationCost()
    {
        return 100;
    }

    @Override
    int fabricationTime() 
    {
        return 50;
    }

    @Override
    int avgUsageTime() 
    {
        return 40;
    }

    @Override
    int yearlyCost() 
    {
        return 1500;
    }

    @Override
    void fix(String a) 
    {
        System.out.println("fixing body " + a);
    }
}
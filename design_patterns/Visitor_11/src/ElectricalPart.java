class ElectricalPart extends AutoPart
{
    @Override
    void accept(Visitor visitor)
    {
        visitor.visit(this);
    }

    @Override
    int fabricationCost()
    {
        return 40;
    }

    @Override
    int fabricationTime()
    {
        return 80;
    }

    @Override
    int avgUsageTime()
    {
        return 10;
    }

    @Override
    int yearlyCost()
    {
        return 500;
    }

    @Override
    void fix(String a)
    {
        System.out.println("fixing electrical " + a);
    }
}
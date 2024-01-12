class SuspentionPart extends AutoPart
{
    @Override
    void accept(Visitor visitor)
    {
        visitor.visit(this);
    }

    @Override
    int fabricationCost()
    {
        return 120;
    }

    @Override
    int fabricationTime()
    {
        return 10;
    }

    @Override
    int avgUsageTime()
    {
        return 50;
    }

    @Override
    int yearlyCost()
    {
        return 1000;
    }

    @Override
    void fix(String a)
    {
        System.out.println("fixing suspension " + a);
    }
}
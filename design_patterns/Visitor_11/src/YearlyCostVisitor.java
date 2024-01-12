public class YearlyCostVisitor implements Visitor
{
    @Override
    public int visit(BodyPart bp)
    {
        return bp.yearlyCost() + 2;
    }

    @Override
    public int visit(ElectricalPart ep)
    {
        return ep.yearlyCost() + 2;
    }

    @Override
    public int visit(SuspentionPart sp)
    {
        return sp.yearlyCost() + 2;
    }
}

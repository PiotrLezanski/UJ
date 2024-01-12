public class FabricationCostVisitor implements Visitor 
{
    @Override
    public int visit(BodyPart bp) 
    {
        return bp.fabricationCost() + 2;
    }

    @Override
    public int visit(ElectricalPart ep) 
    {
        return ep.fabricationCost() + 2;
    }

    @Override
    public int visit(SuspentionPart sp) 
    {
        return sp.fabricationCost() + 2;
    }
}

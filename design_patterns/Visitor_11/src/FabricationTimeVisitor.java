public class FabricationTimeVisitor implements Visitor
{
    @Override
    public int visit(BodyPart bp)
    {
        return bp.fabricationTime() + 2;
    }

    @Override
    public int visit(ElectricalPart ep)
    {
        return ep.fabricationTime() + 2;
    }

    @Override
    public int visit(SuspentionPart sp)
    {
        return sp.fabricationTime() + 2;
    }
}

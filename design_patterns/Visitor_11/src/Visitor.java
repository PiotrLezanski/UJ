public interface Visitor
{
    int visit(BodyPart ep);
    int visit(ElectricalPart ep);
    int visit(SuspentionPart ep);
}

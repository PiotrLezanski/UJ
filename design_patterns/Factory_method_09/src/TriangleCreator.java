public class TriangleCreator implements ShapeCreator
{
    @Override
    public Shape factory() {
        return new Triangle(4, 5);
    }
}

public class TriangleCreator implements ShapeCreator
{
    @Override
    public Shape createSmallShape() {
        return new SmallTriangle(2, 4);
    }

    @Override
    public Shape createBigShape() {
        return new BigTriangle(1, 6);
    }
}

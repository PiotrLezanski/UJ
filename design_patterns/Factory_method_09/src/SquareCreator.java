public class SquareCreator implements ShapeCreator
{
    @Override
    public Shape createSmallShape() {
        return new SmallSquare(3, 4);
    }

    @Override
    public Shape createBigShape() {
        return new BigSquare(4, 3);
    }
}

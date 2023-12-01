public class SquareCreator implements ShapeCreator
{
    @Override
    public Shape factory() {
        return new Square(4, 5);
    }
}

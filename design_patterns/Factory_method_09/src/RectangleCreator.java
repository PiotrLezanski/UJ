public class RectangleCreator implements ShapeCreator
{
    @Override
    public Shape factory() {
        return new Rectangle(4, 5);
    }
}

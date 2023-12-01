public class Square extends Shape
{
    public Square(int x, int y)
    {
        super(x, y);
    }

    @Override
    public void draw()
    {
        System.out.println("Square");
    }
}

public class Main 
{
    public static void main(String[] args) 
    {
        Shape rectangle = new RectangleCreator().factory();
        Shape triangle = new TriangleCreator().factory();
        Shape square = new SquareCreator().factory();
    }
}
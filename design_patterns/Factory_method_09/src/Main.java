public class Main 
{
    public static void main(String[] args) 
    {
        Shape smallTriangle = new TriangleCreator().createSmallShape();
        smallTriangle.draw();
        
        Shape bigSquare = new SquareCreator().createBigShape();
        bigSquare.draw();
    }       
}
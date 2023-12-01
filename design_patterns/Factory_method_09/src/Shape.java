public abstract class Shape 
{
    int x;
    int y;

    public Shape(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public abstract void draw();
}

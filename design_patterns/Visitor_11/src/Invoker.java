public class Invoker
{
    public void invoke(Command c, AutoPart a, String text)
    {
        c.execute(a, text);
    }
}

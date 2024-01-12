public class FixCommand implements Command
{
    @Override
    public void execute(AutoPart a, String text) 
    {
        a.fix(text);
    }
}

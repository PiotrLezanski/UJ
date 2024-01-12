
public class Main {
    public static void main(String[] args) 
    {
        // visitor
        BodyPart[] arr = { new BodyPart(), new BodyPart() };
        Visitor v = new YearlyCostVisitor();

        for(var bodyPart : arr)
        {
            bodyPart.accept(v);
        }
        
        // command
        Invoker invoker = new Invoker();
        invoker.invoke(new FixCommand(), new BodyPart(), "FIX");
    }
}
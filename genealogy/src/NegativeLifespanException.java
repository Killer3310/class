public class NegativeLifespanException extends Exception
{
    @Override
    public String getMessage() 
    {
        return "Lifespan cannot be negative.";
    }
}

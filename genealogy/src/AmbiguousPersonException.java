public class AmbiguousPersonException extends Exception
{
    @Override
    public String getMessage() 
    {
        return "Multiple people with equal names are not allowed.";
    }
}

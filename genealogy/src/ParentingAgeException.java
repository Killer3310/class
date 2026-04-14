public class ParentingAgeException extends Exception
{
    @Override
    public String getMessage() 
    {
        return "Parent is too young.";
    }
}

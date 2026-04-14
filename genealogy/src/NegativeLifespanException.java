public class NegativeLifespanException extends Exception
{
	private Person p;
	public NegativeLifespanException(Person p)
	{
		this.p = p;
	}
    @Override
    public String getMessage() 
    {
        return "Lifespan cannot be negative (" + p.getBirthDate() + " - " + p.getDeathDate() + ").";
    }
}

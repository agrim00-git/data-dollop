//@author Agrim Binjola 1154479
public class InvalidInputException extends Exception
{
    public InvalidInputException()
    {
        super("Invalid Input\n");
    }
    public InvalidInputException(String message)
    {
        super(message);
    }
}

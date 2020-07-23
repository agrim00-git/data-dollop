//@author Agrim Binjola 1154479
public class InvalidMoveException extends Exception
{
    InvalidMoveException()
    {
        super("Invalid Move.");
    }
    InvalidMoveException(String message)
    {
        super(message);
    }
}

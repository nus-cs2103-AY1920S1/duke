/** An exception to be thrown when the command is not legal.*/
public class IllegalCommandException extends Exception {
    public IllegalCommandException(String message) {
        super(message);
    }
}

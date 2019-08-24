package jermi.exception;

public class InvalidCommandException extends JermiException {

    public InvalidCommandException() {
        super("I'm sorry, but I don't know what that means :-(");
    }
}

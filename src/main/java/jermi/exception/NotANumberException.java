package jermi.exception;

public class NotANumberException extends JermiException {

    public NotANumberException() {
        super("The task index is not a number!");
    }
}

public class EmptyInputException extends Exception {
    protected String type;

    public EmptyInputException(String message) {
        super(message);
    }
    
}
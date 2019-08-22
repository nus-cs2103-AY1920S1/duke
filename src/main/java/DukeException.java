@SuppressWarnings("serial")
public class DukeException extends Exception {
    // Stores the command whose parsing led to the exception being thrown
    protected String errorSource;

    public DukeException(String message) {
        super(message);
        this.errorSource = "Unknown error source";
    }

    public DukeException(String message, String errorSource) {
        super(message);
        this.errorSource = errorSource;
    }

    @Override
    public String toString() {
        return String.format("[DukeException] %s\n  > %s", this.getMessage(), this.errorSource); 
    }
}

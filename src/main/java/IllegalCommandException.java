/**
 * This exception is used when User enters an invalid command.
 */
public class IllegalCommandException extends Exception {
    private String errorMessage;

    /**
     * An IllegalCommandException is instantiated when an error message
     * parameter is being passed through.
     * @param errorMessage a String to display the error.
     */
    protected IllegalCommandException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return  " OOPS!!! " + this.errorMessage;
    }
}

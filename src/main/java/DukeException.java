/**
 * This exception is used when there is an empty file
 * in the save .txt file. If there are no tasks in the save file,
 * it will print an error message.
 */
public class DukeException extends Exception {
    private String errorMessage;

    /**
     * An DukeException object will be instantiated and will be thrown to
     * Duke main class.
     * @param errorMessage a String to display the error.
     */
    protected DukeException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return this.errorMessage;
    }
}

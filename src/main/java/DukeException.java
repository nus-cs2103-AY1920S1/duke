/**
 * This exception is used when there is an empty file
 * in the save .txt file. If there are no tasks in the save file,
 * it will print an error message.
 */
public class DukeException extends Exception {
    protected String errormessage;

    /**
     * An DukeException object will be instantiated and will be thrown to
     * Duke main class.
     * @param errormessage a String to display the error.
     */
    public DukeException(String errormessage) {
        this.errormessage = errormessage;
    }

    @Override
    public String toString() {
        return "____________________________________________________________\n"
                + this.errormessage
                + "\n____________________________________________________________";
    }
}

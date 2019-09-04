package duke.helper;

public class DukeException extends Exception {
    String message;

    /**
     * Constructor for DukeException which inherits from Exception. Modifies the string with a sad unicode face.
     *
     * @param errormsg String which contains error.
     */
    public DukeException(String errormsg) {
        String modifiedMsg = "\u2639 " + errormsg;
        this.message = modifiedMsg;
    }

    /**
     * Returns a string containing the error message. Called by to print the error.
     *
     * @return the error message for the Exception.
     */
    public String getMessage() {
        return this.message;
    }
}

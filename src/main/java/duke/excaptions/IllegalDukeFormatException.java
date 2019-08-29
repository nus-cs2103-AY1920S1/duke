package duke.excaptions;

/**
 * exception thrown when deadline or event command gets wrong format
 */
public class IllegalDukeFormatException extends Exception {
    public IllegalDukeFormatException(String command) {
        super("☹ OOPS!!! The format of a " + command + " command is wrong.");
    }
}

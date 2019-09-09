package duke.command;

import duke.error.DukeException;

/**
 * A DukeException to be thrown when the argument(s) provided cannot be parsed as the type required
 * when executing the command.
 */
public class DukeIncorrectParameterTypeException extends DukeException {

    /**
     * Constructs the DukeException to be thrown when the argument(s) given cannot be parsed as the type required.
     *
     * @param typeExpected The expected type of the argument
     * @param argument The argument which cannot be properly parsed
     */
    public DukeIncorrectParameterTypeException(String typeExpected, String argument) {
        super("The following cannot be converted to ", typeExpected, ":\n", argument);
        assert typeExpected != null;
        assert (typeExpected.length() > 0);
        assert argument != null;
    }
}

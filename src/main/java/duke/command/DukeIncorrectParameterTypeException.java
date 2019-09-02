package duke.command;

import duke.error.DukeException;

/**
 * A DukeException which can be thrown when the argument(s), if any, provided cannot be parsed as the type required
 * when executing the command.
 */
public class DukeIncorrectParameterTypeException extends DukeException {

    /**
     * Constructs the DukeException to be thrown when the argument given cannot be parsed as the type required
     *
     * @param typeExpected The expected type of the argument
     * @param argument     The argument cannot be parsed
     */
    public DukeIncorrectParameterTypeException(String typeExpected, String argument) {
        super("The following cannot be converted to ", typeExpected, ":\n", argument);
    }
}

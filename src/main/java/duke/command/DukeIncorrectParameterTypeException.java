package duke.command;

import duke.DukeException;

/**
 * Exception which can be thrown when the type of the argument provided to a command cannot
 * be converted to the required type
 */
public class DukeIncorrectParameterTypeException extends DukeException {

    /**
     * Constructs the exception to be thrown when an argument of the wrong type is provided to a command
     *
     * @param typeExpected The expected type of the argument
     * @param argument     The argument which is of the wrong type
     */
    public DukeIncorrectParameterTypeException(String typeExpected, String argument) {
        super("The following cannot be converted to ", typeExpected, ":\n", argument);
    }
}

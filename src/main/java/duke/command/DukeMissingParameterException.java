package duke.command;

import duke.error.DukeException;

import java.util.ArrayList;

/**
 * A DukeException to be thrown when the parameters required for the Command are not provided
 */
public class DukeMissingParameterException extends DukeException {

    /**
     * Constructor for the DukeException to be thrown when the necessary parameters for a Command are not provided
     *
     * @param type      The type of the command which was not supplied the necessary arguments
     * @param arguments The arguments provided along with the command
     */
    public DukeMissingParameterException(Type type, String[] arguments) {
        super(missingParameters(type, arguments));
    }

    // helper method to get the parameters which are missing arguments
    private static String missingParameters(Type type, String[] argumentsProvided) {
        StringBuilder message = new StringBuilder("The following field(s) cannot be empty:");
        ArrayList<String> parameters = Type.getParametersFor(type);
        for (int i = 0; i < Type.getNumberOfParametersExpectedFor(type); i++) {
            if (argumentsProvided[i] == null) {
                message.append("\n>>>");
                message.append(parameters.get(i));
            }
        }
        return message.toString();
    }
}
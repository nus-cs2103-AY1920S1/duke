package duke.command;

import duke.DukeException;

import java.util.ArrayList;

/**
 * Exception to be thrown when the required parameters for a command are not given an argument
 */
public class DukeMissingParameterException extends DukeException {

    /**
     * Constructor for the exception to be thrown when the necessary arguments for a command are not provided
     * @param type The type of the command which was not supplied the necessary arguments
     * @param arguments The arguments provided along with the command
     */
    public DukeMissingParameterException(Type type, String[] arguments) {
        super(missingParameters(type, arguments));
    }

    // helper method to get the parameters which are missing arguments
    private static String missingParameters(Type type, String[] parametersProvided) {
        StringBuffer message = new StringBuffer("The following field(s) cannot be empty:");
        ArrayList<String> parameters = Type.getParametersFor(type);
        for (int i = 0; i < Type.getNumberOfParametersExpectedFor(type); i++) {
            if (parametersProvided[i] == null) {
                message.append("\n>>>");
                message.append(parameters.get(i));
            }
        }
        return message.toString();
    }
}
package duke.command;

import duke.DukeException;

import java.util.ArrayList;

public class DukeMissingParameterException extends DukeException {
    public DukeMissingParameterException(Type type, String[] parameters) {
        super(missingParameters(type, parameters));
    }

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
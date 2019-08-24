public class DukeMissingParameterException extends DukeException {
    public DukeMissingParameterException(Command.Type type, String[] parameters) {
        super(missingParameters(type, parameters));
    }

    private static String missingParameters(Command.Type type, String[] parametersProvided) {
        StringBuffer message = new StringBuffer("The following field(s) cannot be empty:");
        for (int i = 0; i < type.parametersExpected; i++) {
            if (parametersProvided[i] == null) {
                message.append("\n>>>");
                message.append(type.parameters.get(i));
            }
        }
        return message.toString();
    }
}
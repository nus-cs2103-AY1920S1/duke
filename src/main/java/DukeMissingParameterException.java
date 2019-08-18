public class DukeMissingParameterException extends DukeException {
    public DukeMissingParameterException(Command command) {
        super("A " + command.name()+ " requires the following field(s) to be non-empty: "
                + getRequiredFieldsString(command));
    }

    private static String getRequiredFieldsString(Command command) {
        StringBuffer paramsRequired = new StringBuffer();
        int count = 0;
        for (String param : command.parameters) {
            count++;
            paramsRequired.append("(");
            paramsRequired.append(count);
            paramsRequired.append(")");
            paramsRequired.append(param);
            paramsRequired.append(" ");
        }
        return paramsRequired.toString();
    }
}
public class DukeIncorrectParameterTypeException extends DukeException {
    public DukeIncorrectParameterTypeException(String classExpected, String argument) {
        super("The following cannot be converted to ", classExpected, ":\n", argument);
    }
}

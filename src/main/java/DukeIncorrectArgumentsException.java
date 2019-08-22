@SuppressWarnings("serial")
public class DukeIncorrectArgumentsException extends DukeException {
    protected String commandFormat;

    public DukeIncorrectArgumentsException(
        int numCommandArgs, String commandFormat, int numArgs, String errorSource) {
        super(
            String.format("Expected %d argument(s) for command '%s', but got %d",
                numCommandArgs, errorSource.split(" ", 2)[0], numArgs),
            errorSource
        );
        this.commandFormat = commandFormat;
    }

    @Override
    public String toString() {
        String template = "[DukeIncorrectArgumentsException] %s\n  | %s\n  > %s";
        return String.format(template, this.getMessage(), this.commandFormat, this.errorSource); 
    }
}

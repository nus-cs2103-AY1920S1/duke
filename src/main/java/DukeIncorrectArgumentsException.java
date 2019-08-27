@SuppressWarnings("serial")
public class DukeIncorrectArgumentsException extends DukeException {
    protected String commandFormat;

    /**
     *  Constructs a <code>DukeIncorrectArgumentsException</code> that contains information about a user command that
     *  failed to be parsed due to an incorrect number of arguments.
     *  @param numCommandArgs <code>int</code> number of arguments expected.
     *  @param commandFormat <code>String</code> detailing the expected format.
     *  @param numArgs <code>int</code> number of arguments in the parsed user command.
     *  @param errorSource raw user command <code>String</code> that failed to be parsed.
     */
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

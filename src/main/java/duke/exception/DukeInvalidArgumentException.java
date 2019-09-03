package duke.exception;

@SuppressWarnings("serial")
public class DukeInvalidArgumentException extends DukeException {
    /**
     *  Constructs a <code>DukeInvalidArgumentException</code> that contains information about a user command that
     *  failed to be parsed due to the incorrect type of a supplied argument.
     *  @param argName the name of the argument as a <code>String</code>.
     *  @param argType expected type of the argument as a <code>String</code>.
     *  @param errorSource raw user command <code>String</code> that failed to be parsed.
     */
    public DukeInvalidArgumentException(String argName, String argType, String errorSource) {
        super(
            String.format("Argument '%s' of command '%s' is not of type %s",
                argName, errorSource.split(" ", 2)[0], argType),
            errorSource
        );
    }

    @Override
    public String toString() {
        String template = "[DukeInvalidArgumentException] %s\n  > %s";
        return String.format(template, this.getMessage(), this.errorSource); 
    }
}

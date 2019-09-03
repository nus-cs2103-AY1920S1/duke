package duke.exception;

@SuppressWarnings("serial")
public class DukeUnknownCommandException extends DukeException {
    /**
     *  Constructs a <code>DukeUnknownCommandException</code> that contains information about a user command that
     *  failed to be parsed to a known <code>Command</code> type.
     *  @param errorSource raw user command <code>String</code> that failed to be parsed.
     */
    public DukeUnknownCommandException(String errorSource) {
        // Build the error message from the supplied error command
        super(
            String.format("Unknown command type '%s'", errorSource.split(" ", 2)[0]),
            errorSource
        );
    }

    @Override
    public String toString() {
        String template = "[DukeUnknownCommandException] %s\n  > %s";
        return String.format(template, this.getMessage(), this.errorSource); 
    }
}

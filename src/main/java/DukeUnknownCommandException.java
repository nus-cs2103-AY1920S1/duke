@SuppressWarnings("serial")
public class DukeUnknownCommandException extends DukeException {
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

@SuppressWarnings("serial")
public class DukeInvalidArgumentException extends DukeException {
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

@SuppressWarnings("serial")
public class DukeInvalidTaskException extends DukeException {
    public DukeInvalidTaskException(int id, String errorSource) {
        super(
            String.format("No task exists with ID %d", id),
            errorSource
        );
    }

    @Override
    public String toString() {
        String template = "[DukeInvalidTaskException] %s\n  > %s";
        return String.format(template, this.getMessage(), this.errorSource); 
    }
}

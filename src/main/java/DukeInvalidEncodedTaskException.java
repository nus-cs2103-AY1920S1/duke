@SuppressWarnings("serial")
public class DukeInvalidEncodedTaskException extends DukeException {
    public DukeInvalidEncodedTaskException(
        int numTaskArgs, String taskType, int numArgs, String errorSource) {
        super(
            String.format("Expected %d tokens for encoded task of type '%s', but got %d",
                numTaskArgs, taskType, numArgs),
            errorSource
        );
    }

    @Override
    public String toString() {
        String template = "[DukeInvalidEncodedTaskException] %s\n  > %s";
        return String.format(template, this.getMessage(), this.errorSource); 
    }
}

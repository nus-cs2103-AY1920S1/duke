@SuppressWarnings("serial")
public class DukeInvalidEncodedTaskException extends DukeException {
    /**
     *  Constructs a <code>DukeInvalidEncodedTaskException</code> that contains information about an encoded task that
     *  failed to be parsed due to an incorrect number of tokens.
     *  @param numTaskArgs <code>int</code> number of tokens expected.
     *  @param taskType type of the encoded <code>Task</code> as a <code>String</code>.
     *  @param numArgs <code>int</code> number of arguments in the parsed encoded <code>Task</code>
     *  @param errorSource encoded <code>Task</code> string that failed to be parsed.
     */
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

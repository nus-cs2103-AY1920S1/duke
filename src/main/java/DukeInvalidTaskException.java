@SuppressWarnings("serial")
public class DukeInvalidTaskException extends DukeException {
    /**
     *  Constructs a <code>DukeInvalidTaskException</code> that contains information about a <code>Command</code>
     *  referencing an invalid or non-existent task by its ID.
     *  @param id invalid <code>int</code> task ID referenced.
     *  @param errorSource raw user command <code>String</code> corresponding to the <code>Command</code> that
     *                     failed execution.
     */
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

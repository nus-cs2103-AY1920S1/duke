package duke.exception;

@SuppressWarnings("serial")
public class DukeWriteToFileException extends DukeException {
    /**
     * Constructs a <code>DukeWriteToFileException</code> that contains information about an action that
     * triggered a failed write to the storage File.
     * 
     * @param errorSource <code>String</code> describing the source of the failed write.
     */
    public DukeWriteToFileException(String errorSource) {
        super("Failed to write updated TaskList to file", errorSource);
    }

    @Override
    public String toString() {
        String template = "[DukeWriteToFileException]\n%s";
        return String.format(template, this.getMessage()); 
    }
}

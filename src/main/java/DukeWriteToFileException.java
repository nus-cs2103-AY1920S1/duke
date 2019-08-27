@SuppressWarnings("serial")
public class DukeWriteToFileException extends DukeException {
    public DukeWriteToFileException(String errorSource) {
        super("Failed to write updated TaskList to file", errorSource);
    }

    @Override
    public String toString() {
        String template = "[DukeWriteToFileException] %s";
        return String.format(template, this.getMessage()); 
    }
}

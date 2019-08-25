public class DukeFileWriteException extends DukeExceptions {
    public DukeFileWriteException(String displayMsg) {
        super("Error encountered while writing task data to disk", displayMsg);
    }

    public DukeFileWriteException(String errorMsg, String displayMsg) {
        super(errorMsg, displayMsg);
    }
}

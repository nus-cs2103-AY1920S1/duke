package duke.exception;

public class MissingDateTimeException extends DukeException {
    private String type;

    public MissingDateTimeException(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format("%s Datetime for %s must be specified!", super.toString(), type);
    }
}

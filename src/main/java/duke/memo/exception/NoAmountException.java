package duke.memo.exception;

public class NoAmountException extends DukeException {
    private String recordType;

    public NoAmountException(String recordType) {
        super();
        this.recordType = recordType;
    }

    @Override
    public String getMessage() {
        return "OOPS!!! The amount details of an " + recordType + " cannot be empty.";
    }
}

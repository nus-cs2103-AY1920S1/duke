package duke.exception;

public class UnknownStorageEntryException extends Exception {
    public UnknownStorageEntryException(String message) {
        super(message);
    }

    public UnknownStorageEntryException() {
    }
}

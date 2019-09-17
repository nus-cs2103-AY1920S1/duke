package exception;

public class CorruptedDataException extends DukeException {
    @Override
    public String getMessage() {
        return String.format("%s Data is corrupted!\nStarting tasks from scratch.", super.toString());
    }
}

package exception;

public class CorruptedDataException extends DukeException {
    @Override
    public String getMessage() {
        return String.format("%s I'm sorry, but your saved data is corrupted!\nStarting tasks from scratch.", super.toString());
    }
}

package duke.exception;

public class EmptyListException extends DukeException {
    public EmptyListException() {
        super("Your list is currently empty. Try telling me some tasks!");
    }
}

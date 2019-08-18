package exceptions;

public class EmptyDeadlineArgException extends DukeException {

    public EmptyDeadlineArgException() {
        super(" ☹ OOPS!!! The time of an deadline cannot be empty, e.g. deadline do homework /by August 9");
    }

}

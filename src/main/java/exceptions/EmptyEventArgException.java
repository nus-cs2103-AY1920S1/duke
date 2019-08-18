package exceptions;

public class EmptyEventArgException extends DukeException {

    public EmptyEventArgException() {
        super(" ☹ OOPS!!! The time of an event cannot be empty, e.g. event play tennis /at Monday");
    }
}

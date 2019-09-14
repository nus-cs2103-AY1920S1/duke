package duke.exception;

public class IndexOutOfBoundsDukeException extends DukeException {
    public IndexOutOfBoundsDukeException() {
        super("Index given is out of bound.\nUse from 1 to last index of list only.");
    }
}

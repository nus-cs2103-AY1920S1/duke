package duke.exception;

public class EmptyFieldDukeException extends DukeException {
    public EmptyFieldDukeException(String attrib, String task) {
        super("☹ OOPS!!! The " + attrib + " of a" + (task.matches("^[aeiou].*?") ? "n " : ' ') + task
            + " cannot be empty.");
    }
}

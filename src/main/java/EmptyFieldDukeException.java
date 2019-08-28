public class EmptyFieldDukeException extends DukeException {
    public EmptyFieldDukeException(String attrib, String task) {
        super("☹ OOPS!!! The " + attrib + " of a"
            + (task.startsWith("a|e|i|o|u") ? "n " + task : ' ' + task) + " cannot be empty.");
    }
}

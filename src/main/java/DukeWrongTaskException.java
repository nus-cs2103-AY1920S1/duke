public class DukeWrongTaskException extends DukeException {
    public DukeWrongTaskException(String type) {
        super(String.format("☹ OOPS!!! The description of a %s cannot be empty.", type));
    }
}

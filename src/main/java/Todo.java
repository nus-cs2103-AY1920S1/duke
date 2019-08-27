public class Todo extends Task {

    static Todo of(String description) throws DukeException {
        if (description.length() == 0) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        } else {
            return new Todo(description);
        }
    }

    private Todo(String description) {
        super("T", description);
    }
}

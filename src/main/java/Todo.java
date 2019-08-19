public class Todo extends Task {

    static Todo of(String description) throws DukeException {
        if (description.length() == 0) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        } else {
            return new Todo(description);
        }
    }

    private Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("  [T][%s]%s", getStatusIcon(), getDescription());
    }
}

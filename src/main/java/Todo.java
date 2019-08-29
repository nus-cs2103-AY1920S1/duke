public class Todo extends Task {

    public Todo(String description) throws DukeException {
        super(description);
        if (description.isBlank()) {
            throw new DukeException("OOPS!!! The description of a Todo cannot be empty.");
        }
    }

    @Override
    public String getAscii() {
        return "T | " + super.getAscii();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
public class ToDo extends Task {

    ToDo(String description) throws EmptyDescriptionException {
        super(description);
        if (description.isEmpty()) {
            throw new EmptyDescriptionException("a todo");
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

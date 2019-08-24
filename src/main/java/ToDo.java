public class ToDo extends Task {

    ToDo(String description) throws EmptyDescriptionException {
        super(description);
        if (description.isEmpty()) {
            throw new EmptyDescriptionException("a todo");
        }
    }

    ToDo(String done, String description) {
        super(description);
        if (done.equals("1")) {
            this.markAsDone();
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    String toSaveFormat() {
        return String.format("T|%s", super.toSaveFormat());
    }
}

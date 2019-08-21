package duke;

class Todo extends Task {
    Todo(final String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

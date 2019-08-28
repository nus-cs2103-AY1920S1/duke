package duke;

class Todo extends Task {
    Todo(final String description, final boolean isDone) {
        super(description, isDone);
    }

    Todo(final String description) {
        this(description, false);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    String toStorageString() {
        String[] tokens = new String[]{
            "T",
            this.isDone() ? "1" : "0",
            this.getDescription()
        };
        return String.join(Todo.storageStringSeparator, tokens);
    }
}

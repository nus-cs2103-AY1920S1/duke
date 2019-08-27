class Todo extends Task {
    Todo(String description) {
        super(description);
    }

    Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    String toSaveFormat() {
        return String.format("T | %s", super.toSaveFormat());
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}

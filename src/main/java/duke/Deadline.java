package duke;

class Deadline extends Task {
    private String by;

    Deadline(final String description, final String by, final boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    Deadline(final String description, final String by) {
        this(description, by, false);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }

    @Override
    String toStorageString() {
        String[] tokens = new String[]{
            "D",
            this.isDone() ? "1" : "0",
            this.getDescription(),
            this.by
        };
        return String.join(Todo.storageStringSeparator, tokens);
    }
}

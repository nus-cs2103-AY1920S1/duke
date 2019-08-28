package duke;

class Event extends Task {
    private String at;

    Event(final String description, final String at, final boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    Event(final String description, final String at) {
        this(description, at, false);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }

    @Override
    String toStorageString() {
        String[] tokens = new String[]{
            "E",
            this.isDone() ? "1" : "0",
            this.getDescription(),
            this.at
        };
        return String.join(Todo.storageStringSeparator, tokens);
    }
}

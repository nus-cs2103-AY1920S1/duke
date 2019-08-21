package duke;

class Deadline extends Task {
    private String by;

    Deadline(final String description, final String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}

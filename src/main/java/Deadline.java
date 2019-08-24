class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    public String toSave() { return "D | " + (isDone ? "1" : "0") + " | " + this.description + " | " + this.by; }
}

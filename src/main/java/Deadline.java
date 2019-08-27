class Deadline extends Task {
    private String by;

    /**
     * Creates a new Deadline with the given description and due date.
     * @param description       Task to be completed.
     * @param by                Due date for the Deadline.
     */
    Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the letter "D", representing the type Deadline.
     * @return  "D"
     */
    @Override
    String getType() {
        return "D";
    }

    /**
     * Returns a representation of the current Deadline, including its due
     * date or time, in an appropriate format for data storage.
     * @return  String representing the current Deadline.
     */
    @Override
    String formatAsData() {
        return super.formatAsData() + " | " + by;
    }

    /**
     * Returns a string containing the type of Task, done status, description,
     * and deadline.
     * @return  String describing the Deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

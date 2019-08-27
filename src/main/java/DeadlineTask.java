public class DeadlineTask extends Task {
    protected String byDeadline;

    public DeadlineTask(String description, String byDeadline) {
        super(description);
        this.byDeadline = byDeadline;
    }

    public String getDeadline() {
        return this.byDeadline;
    }

    public String toEncodedString() {
        return String.format(
            "D | %d | %s | %s",
            this.isComplete ? 1 : 0,
            this.description,
            this.byDeadline
        );
    }

    @Override
    public String toString() {
        // Adds the type of the Task and its deadline to the base toString() representation
        return String.format("[D]%s (by: %s)", super.toString(), this.byDeadline);
    }
}

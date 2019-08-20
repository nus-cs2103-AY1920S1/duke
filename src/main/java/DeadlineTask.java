public class DeadlineTask extends Task {
    protected String time;

    public DeadlineTask(String description, String time) {
        super(description);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[D][" + this.getStatusIcon() + "] " + this.description
                + " (by: " + this.time + ")";
    }
}

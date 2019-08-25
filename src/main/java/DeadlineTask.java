public class DeadlineTask extends Task {
    protected String time;

    public DeadlineTask(String description, String time) {
        super(description);
        this.time = time;
    }

    @Override
    public String formattedString() {
        return "D | " + super.formattedString() + " | " + this.time + "\n";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.time + ")";
    }
}

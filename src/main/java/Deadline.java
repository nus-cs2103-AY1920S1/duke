public class Deadline extends Task{
    private String timeOfDeadline;
    public Deadline(String s, String time) {
        super(s);
        this.timeOfDeadline = time;
    }

    public String toString() {
        return "[D]" + this.getStatusIcon() + this.description + " (by: " + this.timeOfDeadline + ")";
    }
}


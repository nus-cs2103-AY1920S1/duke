public class DeadLine extends Task{
    private String time;
    public DeadLine(String s, String t) {
        super(s);
        this.time = t;
    }

    public String toString() {
        return "[D]" + this.getStatusIcon() + this.description + " (by: " + this.time + ")";
    }
}

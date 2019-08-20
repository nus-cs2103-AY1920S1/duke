public class Deadline extends Task {
    private String time;

    public Deadline(String description) {
        super(description);
        this.type = "D";
        this.time = "";
    }

    public void setTime(String time) {
        this.time = " (by: " + time.substring(3) + ")";
    }

    @Override
    public String toString() {
        return "[" + this.type + "][" + this.getStatusIcon() + "] " + this.description + this.time;
    }
}

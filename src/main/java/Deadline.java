public class Deadline extends Task {
    private String time;

    public Deadline(String des, String time) {
        super(des);
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + time + ")";
    }
}

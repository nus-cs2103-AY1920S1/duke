public class Deadline extends Task {
    private String time;

    public Deadline(String des, String time) {
        super(des);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + time + ")";
    }
}

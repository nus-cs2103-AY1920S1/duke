public class Deadline extends Task {
    public String time;
    public Deadline(String task, String time) {
        super(task);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.time + ")";
    }
}

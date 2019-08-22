public class Deadline extends Task {
    private String time;
    public Deadline(String name, boolean isDone, String time) {
        super(name, isDone);
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] " + getName() + " (by: " + time  + ")";    }
}

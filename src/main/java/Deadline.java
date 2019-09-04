public class Deadline extends Task {
    String time;
    public Deadline(String description, String time) {
        super(description);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[D][" + this.getMark() + "] " + this.getDescription() + " (by: " + time + ")\n";
    }
}

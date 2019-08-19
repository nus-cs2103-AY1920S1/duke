package duke.task;

public class Deadline extends Task {
    String time;

    /**
     * Constructor.
     */
    public Deadline(String name, String time) {
        super(name);
        this.time = time;
    }

    @Override
    /**
     * Overrides toString method.
     * @return String
     */
    public String toString() {
        String status;
        if (this.isDone) {
            status = "[✓]";
        } else {
            status = "[✗]";
        }
        return String.format("[D]%s %s (by: %s)", status, this.name, this.time);
    }
}

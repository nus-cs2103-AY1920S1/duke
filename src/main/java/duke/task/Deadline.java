package duke.task;

public class Deadline extends Task {
    protected String time;

    public Deadline(String name, String time, boolean isDone) {
        super(name, isDone);
        this.time = time;
    }

    public String getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        return String.format("[D] | %s | by: %s", super.toString(), this.time);
    }
}

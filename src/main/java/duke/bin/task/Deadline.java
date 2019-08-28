package duke.bin.task;

public class Deadline extends Task {
    protected String description;

    public Deadline(String task, String description) {
        super(task);
        this.description = description;
    }

    public String getType() {
        return "D";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + description + ")";
    }
}


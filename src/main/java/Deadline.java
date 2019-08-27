package seedu.duke;

public class Deadline extends Task {

    protected String by;

    public Deadline(String task, String by) {
        super(task);
        this.by = by;
    }

    public Deadline(String task, String done, String by) {
        super(task);
        this.by = by;
        if (done.equals("1")) {
            super.markAsDone();
        }
    }

    public String toStorageString() {
        String output = "D|";
        if (super.isDone) {
            output = output + "1|";
        } else {
            output = output + "0|";
        }
        output = output + super.taskName + "|" + this.by;
        return output;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

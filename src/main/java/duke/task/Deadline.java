package duke.task;

public class Deadline extends Task {

    // the Deadline subclass adds one field
    public String by;

    // the Deadline subclass has one constructor
    public Deadline(String item, String by) {
        super(item);
        this.by = by;
    }

    @Override
    // overrides toString method in Task
    public String toString() {
        if (isDone) {
            return "[D][" + "/" + "] " + super.toString() + "(by:" + by + ")";
        } else {
            return "[D][" + "x" + "] " + super.toString() + "(by:" + by + ")";
        }
    }

    @Override
    // overrides saveTask method in Task
    public String saveTask() {
        if (isDone) {
            return "D" + " | " + "1" + " | " + super.toString() + " | " + by + "\n";
        } else {
            return "D" + " | " + "0" + " | " + super.toString() + " | " + by + "\n";
        }
    }

}

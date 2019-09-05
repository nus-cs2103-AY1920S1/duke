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
            return "[D][" + "\u2713" + "] " + super.toString() + "(by:" + by + ")";
        } else {
            return "[D][" + "\u2718" + "] " + super.toString() + "(by:" + by + ")";
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

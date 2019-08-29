public class Deadline extends Task {

    public String by;

    public Deadline(String item, String by) {
        super(item);
        this.by = by;
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[D][" + "\u2713" + "] " + super.toString() + "(by:" + by + ")";
        } else {
            return "[D][" + "\u2718" + "] " + super.toString() + "(by:" + by + ")";
        }
    }

}

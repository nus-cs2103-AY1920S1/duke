public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.type = "deadline";
    }

    public Deadline(boolean isDone, String description, String by) {
    	super(isDone, description); // usually isDone = true here
    	this.by = by;
    	this.type = "deadline";
    }

    public void changeTime(String time) {
        this.by = time;
    }

    @Override
    public String getDescription() {
    	return this.description + " /by " + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
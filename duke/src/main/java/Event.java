public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        this.type = "event";
    }

    public Event(boolean isDone, String description, String at) {
    	super(isDone, description); // usually isDone = true here
    	this.at = at;
    	this.type = "event";
    }

    @Override
    public String getDescription() {
    	return this.description + " /at " + this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
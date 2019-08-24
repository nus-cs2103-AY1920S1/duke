public class EventTask extends Task {

    protected String at;

    public EventTask(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toFileString() {
        return "T\t" + (this.isDone ? "1\t" : "0\t") + this.description + "\t" + this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
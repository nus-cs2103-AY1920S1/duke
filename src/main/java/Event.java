public class Event extends Task {
    protected DateTime at;

    public Event(String description, DateTime at) {
        super(description);
        this.at = at;
        super.changeToFileFormat('E', description, at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + at +")";
    }
}

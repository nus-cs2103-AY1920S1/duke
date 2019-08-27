public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public Event(String description, boolean isDone, String at) {
        super(description, isDone);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    /**
     * formats the Task into savable format
     * @return String that can be saved into a txt file
     */
    public String toFormattedString() {
        return "E~" + super.toFormattedString() + "~" + at;
    }
    
}
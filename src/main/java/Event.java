public class Event extends Task {
    String timeline;

    public Event (String task, String timeline) {
        super(task);
        this.timeline = timeline;
    }

    public Event (String task, String timeline, boolean complete) {
        super(task, complete);
        this.timeline = timeline;
    }

    @Override
    public String toStringForFile() {
        String isComplete = complete ? "1" : "0";
        return "E | " + isComplete + " | " + task + " | " + timeline; 
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + timeline + ")";
    }
}
public class Event extends Task {

    protected String time;

    public Event(String taskName, String time) {
        super(taskName);
        this.time = time;
    }

    @Override
    public String toString() {
        return taskID + ".[E]" + super.toStringNoID() + " (at: " + time + ")" + "\n";
    }

    @Override
    public String toStringNoID() {
        return "[E]" + super.toStringNoID() + " (at: " + time + ")" + "\n";
    }
}

public class Event extends Task {

    protected String time;

    public Event(String taskName, String time) {
        super(taskName);
        this.time = time;
    }

    public Event(Status status, String taskName, String time) {
        super(status, taskName);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time + ")" + "\n";
    }

    public String toSaveString() {
        return "E|" + (super.completed == Status.INCOMPLETE ? "0" : "1") + "|" + taskName + "|" + time;
    }

}

package duke.task;

public class Event extends Task {
    protected String time;

    public Event(String name, String time, boolean isDone) {
        super(name, isDone);
        this.time = time;
    }

    public String getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        return String.format("[E] | %s | at: %s", super.toString(), this.time);
    }
}

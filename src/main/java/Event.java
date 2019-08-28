public class Event extends Task {
    protected String time;

    public Event(String description, String time) {
        super(description);
        this.type = Type.E;
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("%s (at: %s)", super.toString(), time);
    }

    @Override
    public String toFile() {
        String doneState = isDone ? "1" : "0";
        return String.format("%s //| %s //| %s //| %s", type, doneState, description, time);
    }
}

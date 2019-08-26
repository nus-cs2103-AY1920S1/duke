public class EventTask extends Task {
    protected String time;

    public EventTask(String description, String time) {
        super(description);
        this.time = time;
    }

    @Override
    public String formattedString() {
        return "E | " + super.formattedString() + " | " + this.time + "\n";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.time + ")";
    }
}

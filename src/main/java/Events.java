public class Events extends Task {
    protected String at;
    protected String event[];

    public Events(String description, String at) {
        super(description);
        this.at = at;
        event = at.split(" ", 2);
    }

    @Override
    public String formatString() {
        return "E-" + super.checkStatus() + "-" + super.getDescription().trim() + "-" + this.event[1];
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + event[1] + ")";
    }
}

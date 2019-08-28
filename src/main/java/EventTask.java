public class EventTask extends Task {

    public EventTask(String description, String startDate) {
        super(description);
        this.dateTime = startDate;
        this.type = "E";
    }

    public EventTask(String description, String startDate, boolean isDone) {
        super(description, isDone);
        this.dateTime = startDate;
        this.type = "E";
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.dateTime);
    }
}

public class EventTask extends Task {
    private String startDate;

    public EventTask(String description, String startDate) {
        super(description);
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.startDate);
    }
}

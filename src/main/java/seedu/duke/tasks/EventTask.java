package seedu.duke.tasks;

public class EventTask extends Task {
    private final String time;

    public EventTask(String description, String time) {
        super(description);
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return String.format(
                "[E]%s%s", super.toString(),
                time != null && !time.isBlank()
                        ? String.format(" (at: %s)", time)
                        : ""
        );
    }
}

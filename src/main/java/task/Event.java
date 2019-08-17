package task;

public class Event extends Task {
    protected String slashKeyword;
    protected String time;

    /**
     * Creates a new event task.
     * @param description Description of the task
     * @param slashKeyword Keyword right after the slash, which should equal 'at'
     * @param time Time of the event
     */
    public Event(String description, String slashKeyword, String time) {
        super(description);
        this.slashKeyword = slashKeyword;
        this.time = time;
    }

    public String getTaskInitial() {
        return "E";
    }

    public boolean isValid() {
        return this.description != null && this.slashKeyword.equals("at") && this.time != null;
    }

    @Override
    public String extraText() {
        return " (at: " + this.time + ")";
    }
}

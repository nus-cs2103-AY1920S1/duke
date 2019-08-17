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

    @Override
    public String getTaskInitial() {
        return "E";
    }

    @Override
    public boolean isValid() {
        return this.description != null && this.slashKeyword != null && this.slashKeyword.equals("at")
            && this.time != null;
    }

    @Override
    public String invalidMessage() {
        if (this.description == null) {
            return "The description of an event cannot be empty.";
        } else {
            return "An event must have a date or time.";
        }
    }

    @Override
    public String extraText() {
        return " (at: " + this.time + ")";
    }
}

package task;

public class Event extends Task {
    protected String slashKeyword;
    protected String time;

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

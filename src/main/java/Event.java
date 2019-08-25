public class Event extends Task {
    protected String time;
    public Event (String description, String time) {
        super(description);
        this.time = time;
    }

    public String toFileString() {
        return "E||" + (this.isDone?"1||":"0||")  + this.description + "||" + this.time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at:" + time + ")";
    }
}
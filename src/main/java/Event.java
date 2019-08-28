public class Event extends Task {
    String type;
    String date;

    public Event(String taskName, String date) {
        super(taskName);
        type = "event";
        this.date = date;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getTypeIcon() {
        return "[E]";
    }

    @Override
    public String getDate() {
        return date;
    }
}

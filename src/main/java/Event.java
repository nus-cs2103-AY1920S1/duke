public class Event extends Task{
    public Event(String description, String at) {
        super(description.trim());
        super.tag = "[E]";
        super.information = "(at: " + at.trim() + ")";
    }
}

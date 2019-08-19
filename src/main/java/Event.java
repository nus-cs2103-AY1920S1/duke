public class Event extends Task {
    protected String duration;

    public Event(String description, String duration) {
        super(description);
        this.typeOfTask = "E";
        this.duration = duration;
    }
}

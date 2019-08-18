public class Event extends Task {
    private String datetime;

    Event(String description, String datetime) throws EmptyDescriptionException{
        super(description);
        if (description.isEmpty()) {
            throw new EmptyDescriptionException("an event");
        }
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.datetime);
    }
}

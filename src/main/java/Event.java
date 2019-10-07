public class Event extends Task {

    protected String at;

    /**
     * Constructor for Event task.
     *
     * @param description description of the event.
     * @param at date and time of the Event in String format
     *
     */

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String date() {
        return this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
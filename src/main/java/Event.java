public class Event extends Task {

    protected String at;

    public Event(String description, String at) throws DukeException {
        super(description);
        if (at.equals("")) {
            throw new DukeException("The date/time of " + this.getTypeNameWithQuantifier() + " cannot be empty.");
        }
        this.at = at;
    }

    protected String getTypeNameWithQuantifier() {
        return "an event";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}

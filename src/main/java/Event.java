/**
 * This class represents Event tasks which is a type of Task.
 */
public class Event extends Task {
    DateTime at;

    /**
     * Constructor for Events.
     * @param description Event description
     * @param at String representation of date of event
     */
    public Event(String description, String at) {
        super(description);
        this.typeOfTask = "[E]";
        this.at = new DateTime(at);
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + at.toString() + ")";
    }

    @Override
    public String writeToFile() {
        return super.writeToFile() + "|" + at.getRawForm();
    }
}

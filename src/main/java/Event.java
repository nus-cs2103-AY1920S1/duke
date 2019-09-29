/**
 * Represents a different type of Task called Event that holds an
 * additional parameter for the venue.
 */
public class Event extends Task {

    protected String at;

    /**
     * Constructor to set the description of event and venue.
     *
     * @param desc The description of the Event
     * @param at   The venue of the Event
     */
    public Event(String desc, String at) {
        super(desc);
        this.at = at;
    }

    /**
     * Constructor with additional parameter to set its completion status.
     *
     * @param desc   The description of the Event
     * @param at     The venue of the Event
     * @param isDone The boolean variable to note if Event is completed
     */
    public Event(String desc, String at, boolean isDone) {
        super(desc, isDone);
        this.at = at;
    }

    /**
     * Overridden writeFormat method to specify that it is a Event
     * when saving the data.
     *
     * @return Format for saving data
     */
    @Override
    public String writeFormat() {
        return "E " + isDone + " " + description + "/" + at;
    }

    /**
     * Overridden toString method to print out Event object.
     *
     * @return Printing format of Event
     */
    @Override
    public String toString() {
        return "[E]" + super.getTask() + " (at: " + at + ")";
    }
}

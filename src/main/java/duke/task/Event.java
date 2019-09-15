package duke.task;

/**
 * Event class.
 */
public class Event extends Task {
    private String date;


    /**
     * Constructor for Event object.
     *
     * @param n name of event
     * @param date date of event
     */
    public Event(String n, String date) {
        super(n);
        this.date = date;
    }


    /**
     * Constructor for Event object when loading history.
     *
     * @param n name of event
     * @param date date of event
     * @param completed true if event is already completed, false otherwise
     */
    public Event(String n, String date, boolean completed) {
        super(n, completed);
        this.date = date;
    }

    /**
     * Returns date of event.
     *
     * @return String of date
     */
    public String getDate() {
        return date;
    }


    /**
     * Returns string representation of Event object.
     *
     * @return String representation of Event object
     */
    @Override
    public String toString() {
        String result = "[E][";
        result += super.toString();
        result += " (at: " + this.date + ")";
        return result;
    }
}

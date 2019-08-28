//package mypackage;

/**
 * Represents the event task.
 */
public class Event extends Task {

    /**
     * The date of the event.
     */
    protected Date date;

    /**
     * Constructs the event task.
     * @param description description of the event
     * @param at date of the event
     */
    public Event(String description, String at) {
        super(description);
        String[] dateAndTime = new String[2];
        dateAndTime = at.split(" ");
        String[] date = new String[3];
        date = dateAndTime[0].split("/");
        this.date = new Date(Integer.parseInt(date[0]), Integer.parseInt(date[1]),
                Integer.parseInt(date[2]), Integer.parseInt(dateAndTime[1]));
    }

    /**
     * Gets the date of the event
     * @return the event date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Returns the event task and the date.
     * @returns the event task and the date
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date + ")";
    }
}

package task;

public class Event extends Task {
    Date date;
    Time time;

    /**
     * Constructor for an Event object.
     * @param description String description of the event.
     * @param date String date of the event. (Example: "18/08/2019").
     * @param time String time of the event. (Example: "1600").
     */
    public Event(String description, String date, String time) {
        super(description.trim());
        this.date = new Date(date);
        this.time = new Time(time);
    }

    /**
     * toString() returns the String that is represented by this task.
     * @return String.
     */
    @Override
    public String toString() {
        String displayDate = "at: " + this.date.toString();
        return "[E][" + super.getStatusIcon() + "] "
                + super.description + " (" + displayDate + " " + this.time.toString() + ")";
    }

    /**
     * toDataFormat() returns the String that is used to write the Storage file.
     * @return String.
     */
    @Override
    public String toDataFormat() {
        return "E | " + super.getStatusIcon() + " | " + super.description + " | " + this.date.origin() + " | " + this.time.origin();
    }
}

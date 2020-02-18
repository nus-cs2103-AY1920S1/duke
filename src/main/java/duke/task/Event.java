package duke.task;

/**
 * Represents a type of task known as Event.
 */
public class Event extends Task {

    // the Event subclass adds two fields
    private String at;
    private String end;

    /**
     * Initialises an Event.
     * An Event consists of the task itself, starting time and ending time.
     *
     * @param item name of the task.
     * @param at starting time of the task.
     * @param end ending time of the task.
     */
    // the Event subclass has one constructor
    public Event(String item, String at, String end) {
        super(item);
        this.at = at;
        this.end = end;
    }

    /**
     * Prints an Event as a string to the user in a specific format.
     *
     * @return Event.
     */
    @Override
    // overrides toString method in Task
    public String toString() {
        if (isDone) {
            return "[E][" + "/" + "] " + super.toString() + " (at: " + at + " - " + end + ")";
        } else {
            return "[E][" + "x" + "] " + super.toString() + " (at: " + at + " - " + end + ")";
        }
    }

    /**
     * Saves an Event as a string to the file in a specific format.
     *
     * @return Event.
     */
    @Override
    // overrides saveTask method in Task
    public String saveTask() {
        if (isDone) {
            return "E" + " | " + "1" + " | " + super.toString() + " | " + at + " | " + end + "\n";
        } else {
            return "E" + " | " + "0" + " | " + super.toString() + " | " + at + " | " + end + "\n";
        }
    }

}

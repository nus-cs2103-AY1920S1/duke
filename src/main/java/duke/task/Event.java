package duke.task;

/**
 * Represents a type of task known as Event.
 */
public class Event extends Task {

    // the Event subclass adds one field
    public String at;

    /**
     * Initialises an Event.
     * An Event consists of the task itself and a time when it will happen.
     *
     * @param item name of the task.
     * @param at time of the task.
     */
    // the Event subclass has one constructor
    public Event(String item, String at) {
        super(item);
        this.at = at;
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
            return "[E][" + "/" + "] " + super.toString() + "(at:" + at + ")";
        } else {
            return "[E][" + "x" + "] " + super.toString() + "(at:" + at + ")";
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
            return "E" + " | " + "1" + " | " + super.toString() + " | " + at + "\n";
        } else {
            return "E" + " | " + "0" + " | " + super.toString() + " | " + at + "\n";
        }
    }

}

package duke.task;

/**
 * Represents a task of type event. An <code>Event</code> object contains a description,
 * a  boolean representing whether or not the task has been done and additional information.
 */
public class Event extends Task {
    protected String at;

    /**
     * Constructs a new Event.
     * @param description description of the task
     * @param isDone whether or not the task has been done
     */
    public Event(String description, boolean isDone,  String info) {
        super(description, isDone, info);
        super.type = Type.EVENT;
        String[] infos = info.split(" ", 2);
        this.at =  super.checkDate(infos[1]);
    }

    /**
     * Overrides toString method.
     * @return a String in the to-be-displayed format
     */
    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + description + " (at: " + at + ")";
    }

    /**
     * Returns a String in the format for file saving.
     * @return a string in the saved format
     */
    @Override
    public String getFileStringFormat() {
        if (isDone()) {
            return "E | 1 | " + description + " | " + info;
        } else {
            return "E | 0 | " + description + " | " + info;
        }
    }
}

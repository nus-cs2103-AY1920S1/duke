package duke.tasks;

/**
 * Represents an Event Task.
 */

public class Event extends Task {

    String duration;

    /**
     * Creates a deadline object.
     * @param taskName name of the event
     * @param isDone whether the event has been completed
     * @param duration timing of event
     */

    public Event(String taskName, boolean isDone, String duration) {
        super(taskName, isDone);
        this.duration = duration;
    }

    public void setDuration(String newDuration){
        this.duration = newDuration;
    }

    /**
     * Returns a string to represent the event in the storage file.
     * @return Event String in file form
     */

    @Override
    public String toFile() {
        String mark = isDone ? "1" : "0";
        return "E | " + mark + " |" + taskName + "|" + duration;
    }

    /**
     * Returns event in string form to be printed.
     * @return Event String in print form
     */

    @Override
    public String toString() {
        String mark = isDone ? "\u2713" : "\u2718";
        return "[E][" + mark + "]" + taskName
                + "(at:" + duration + ")";
    }
}

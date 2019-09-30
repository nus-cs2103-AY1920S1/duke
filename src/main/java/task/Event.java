package task;

/**
 * Tasks.Event class which inherits from the Tasks.Task class and is used to represent
 * events within Core.Duke.
 */

public class Event extends Task {

    String timeline;

    /**
     * Creates a new Tasks.Event object.
     * @param task Description of the event to take place.
     * @param timeline Description of the duration of the event.
     */
    public Event(String task, String timeline) {
        super(task);
        this.timeline = timeline;
    }

    /**
     * Creates a new Tasks.Event object for use within Core.Duke,
     * with a predermined completion state of whether 
     * it is complete or not.
     * @param task Description of the event to take place.
     * @param timeline Description of the duration of the event.
     * @param complete Boolean variable to determine if the deadline has been finished or not.
     */
    public Event(String task, String timeline, boolean complete) {
        super(task, complete);
        this.timeline = timeline;
    }

    /**
     * Returns the String representation of an event as 
     * it is stored in text file on the local system.
     */
    @Override
    public String toStringForFile() {
        String isComplete = complete ? "1" : "0";
        return "E | " + isComplete + " | " + task + " | " + timeline; 
    }

    /**
     * Returns the String representation of an event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + timeline + ")";
    }
}
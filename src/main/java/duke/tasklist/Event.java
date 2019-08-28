package duke.tasklist;

import duke.DukeException;
import duke.io.Parser;

/**
 * Class representing an Event task in the task list
 */
public class Event extends Task {
    public String time;

    /**
     * Constructs an Event task which is incomplete initially
     *
     * @param description The description of the event
     * @param time        The time the event occurs at
     */
    public Event(String description, String time) {
        super(description);
        try {
            this.time = Parser.parseDateTime(time);
        } catch (DukeException e) {
            this.time = time;
        }
    }

    /**
     * Constructs an Event task with the completion status provided
     *
     * @param isComplete  The completion status of the Event
     * @param description The description of the event
     * @param time        The time the event occurs
     */
    public Event(boolean isComplete, String description, String time) {
        super(description);
        try {
            this.time = Parser.parseDateTime(time);
        } catch (DukeException e) {
            this.time = time;
        }
        taskCompletionStatus = isComplete;
    }

    /**
     * Returns the string representation of the task
     *
     * @return The string representation of the task
     */
    @Override
    public String toString() {
        return new StringBuilder("[E]").append(super.toString())
                .append(" (at: ")
                .append(time)
                .append(")")
                .toString();
    }

}

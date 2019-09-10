package duke.task;

import java.util.ArrayList;
import java.util.Date;

/**
 * An object representation of a user task occurring at a specified time.
 */
public class EventTask extends Task {
    protected Date atTime;

    /**
     * Constructs an <code>EventTask</code> object with a given description and event timestamp.
     * 
     * @param description <code>String</code> description of this <code>Task</code>.
     * @param atTime timestamp of this <code>EventTask</code>, as a <code>Date</code> object.
     * @param tags an <code>ArrayList</code> of <code>String</code> tags for this task.
     */
    public EventTask(String description, Date atTime, ArrayList<String> tags) {
        super(description, tags);
        this.atTime = atTime;
    }

    /**
     * Returns the timestamp of this <code>EventTask</code>.
     * 
     * @return timestamp of this <code>EventTask</code>, as a <code>Date</code>.
     */
    public Date getTime() {
        return this.atTime;
    }

    /**
     * {@inheritDoc}
     */
    public String toEncodedString() {
        return String.format(
            "E | %d | %s | %s",
            this.isComplete ? 1 : 0,
            this.description,
            Task.DATE_WRITER.format(this.atTime)
        );
    }

    @Override
    public String toString() {
        // Adds the type of the Task and its time period to the base toString() representation
        return String.format("[E]%s (at: %s)", super.toString(), Task.DATE_FORMATTER.format(this.atTime));
    }
}

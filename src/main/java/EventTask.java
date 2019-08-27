/**
 * A class representing an event as a <code>Task</code>. Contains date-time information for when the event will start
 * and end.
 */
public class EventTask extends Task {
    private final DukeDuration eventDuration;

    /**
     * Creates an instance of an <code>EventTask</code>. The completion status is set to <code>false</code> by default.
     * 
     * @param description The description of this <code>Task</code>
     * @param eventDuration The duration over which the event will take place
     */
    public EventTask(String description, DukeDuration eventDuration) {
        this.description = description;
        this.eventDuration = eventDuration;
        this.isDone = false;
    }

    /**
     * Creates an instance of an <code>EventTask</code>. Allows the caller to set its completion status.
     * 
     * @param description The description of this <code>Task</code>
     * @param deadlineTime The duration over which the event will take place
     * @param isDone The completion status of the <code>Task</code>
     */
    public EventTask(String description, DukeDuration eventDuration, boolean isDone) {
        this.description = description;
        this.eventDuration = eventDuration;
        this.isDone = isDone;
    }

    @Override
    //Returns a copy of this task but with its completion status marked as done
    public Task getTaskMarkedAsDone() {
        return new EventTask(description, eventDuration, true);
    }

    @Override
    //Returns a copy of this task but with its completion status marked as undone
    public Task getTaskMarkedUndone() {
        return new EventTask(description, eventDuration, false);
    }

    /**
     * @return The <code>String</code> representation of this <code>Task</code>, containing the type of <code>Task</code>,
     * completion status and description
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(TO_STRING_FORMAT, 'E', this.getStatusIcon(), this.description));
        sb.append(String.format(" (at %s)", eventDuration.toString()));
        return sb.toString();
    }
}

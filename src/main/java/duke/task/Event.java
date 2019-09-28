package duke.task;

import java.util.Date;

import duke.task.Task;

public class Event extends Task {
    protected Date from;
    protected Date to;

    /**
     * The constructor for the Event type task.
     * @param description The description of the event.
     * @param from The date and time that the event starts.
     * @param to The date and time that the event ends.
     */
    public Event(String description, Date from, Date to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getFileLine() {
        return super.getFileLine() + " | " + this.from + " - " + this.to;
    }

    @Override
    public String getType() {
        return "E";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.from + " - " + this.to + ")";
    }

    /**
     * This function compares between two Event tasks by their description, from date and then the to date.
     * @param other The other Event task to compare to.
     * @return int
     */
    @Override
    public int compareTo(Task other) {
        if (!this.getType().equals(other.getType())) {
            return -1;
        }

        Event comp = (Event) other;

        if (this.description.compareTo(comp.description) == 0) {
            if (this.from.compareTo(comp.from) == 0) {
                return this.to.compareTo(comp.to);
            } else {
                return this.from.compareTo(comp.from);
            }
        } else {
            return this.description.compareTo(comp.description);
        }
    }
}

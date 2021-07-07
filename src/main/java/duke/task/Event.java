package duke.task;

import java.util.Date;
import java.util.function.Function;

/** Represents an event task. */
public class Event extends Task {
    private Date start;
    private Date end;

    /**
     * Creates an {@link Event} that comprises a start and end time.
     *
     * @param description Description of the event.
     * @param start Starting time of the event.
     * @param end Ending time of the event.
     */
    public Event(String description, Date start, Date end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns the duration of the {@link Event} separated by a dash.
     *
     * @return A string representation of the duration of the event.
     */
    public String getDurationString() {
        return String.format(
                "%02d/%02d/%04d %02d%02d - %02d/%02d/%04d %02d%02d",
                start.getDate(),
                start.getMonth() + 1,
                start.getYear() + 1900,
                start.getHours(),
                start.getMinutes(),
                end.getDate(),
                end.getMonth() + 1,
                end.getYear() + 1900,
                end.getHours(),
                end.getMinutes());
    }

    /**
     * Returns a string formatted to style suitable for {@link duke.util.Storage}.
     *
     * @return String formatted for {@link duke.util.Storage}.
     */
    @Override
    public String getStorageSyntax() {
        int isDoneInteger = super.isDone ? 1 : 0;
        return String.format("E | %d | %s | %s | %d", isDoneInteger, this.description,
                this.getDurationString(), this.priority);

    }

    /**
     * Returns a description of the properties of the {@link Task}.
     *
     * @return a string representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), getDurationString());
    }
}

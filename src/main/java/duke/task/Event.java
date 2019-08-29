package duke.task;

import java.time.LocalDateTime;

public class Event extends Task {
    protected LocalDateTime startDate;
    protected LocalDateTime endDate;

    public Event(String desc) {
        super(desc);
    }

    public Event(String desc, boolean done) {
        super(desc, done);
    }

    /**
     * Constructor method.
     *
     * @param desc Event description
     * @param startDate Event start datetime
     * @param endDate Event end datetime
     */
    public Event(String desc, LocalDateTime startDate, LocalDateTime endDate) {
        super(desc);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Constructor method.
     *
     * @param desc Event description
     * @param startDate Event start datetime
     * @param endDate Event end datetime
     * @param done Event done state
     */
    public Event(String desc, LocalDateTime startDate, LocalDateTime endDate, boolean done) {
        super(desc, done);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Getter for startDate variable.
     *
     * @return Start date of event.
     */
    public LocalDateTime getStartDate() {
        return startDate;
    }

    /**
     * Setter for startDate variable.
     *
     * @param startDate Start date of event.
     */
    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    /**
     * Getter for endDate variable.
     *
     * @return End date of event.
     */
    public LocalDateTime getEndDate() {
        return endDate;
    }

    /**
     * Setter for endDate variable.
     *
     * @param endDate End date of event.
     */
    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.getStartDate() + " to " + this.getEndDate() + ")";
    }
}

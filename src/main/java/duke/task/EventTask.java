package duke.task;

import duke.date.DukeDate;

/**
 * Represents an event task inputted by the user.
 */
public class EventTask extends Task {

    /** Stores the date of the event. */
    private DukeDate time;

    /**
     * Constructs an EventTask object with the given description and {@link DukeDate}.
     * isDone field is set to false by default.
     *
     * @param description The description of this EventTask.
     * @param time The time of this EventTask.
     */
    public EventTask(String description, DukeDate time) {
        super(description);
        this.time = time;
    }

    /**
     * Constructs an EventTask object with the given description, status, and {@link DukeDate}.
     *
     * @param description The description of this EventTask.
     * @param isDone The status of this EventTask.
     * @param time The time of this EventTask.
     */
    public EventTask(String description, boolean isDone, DukeDate time) {
        super(description, isDone);
        this.time = time;
    }

    public DukeDate getTime() {
        return this.time;
    }

    /**
     * Returns the date field formatted as a String.
     * The format is "dd Month, YYYY, hh:mm a".
     *
     * @return The date field formatted as a String.
     * @see DukeDate#format(boolean)
     */
    public String getDateAsString() {
        return this.time.format(true);
    }

    /**
     * Returns the type of this DeadlineTask as a single capital letter.
     *
     * @return The letter E.
     */
    @Override
    public String getType() {
        return "E";
    }

    /**
     * Returns the type, status, description, and date of this EventTask.
     *
     * @return The type, status, description, and date formatted as a String.
     */
    @Override
    public String getStatus() {
        return String.format("[E]%s (at: %s)",
                             super.getStatus(),
                             this.time.format(true));
    }

}

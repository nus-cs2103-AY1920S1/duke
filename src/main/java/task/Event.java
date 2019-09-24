package task;

import run.Ui;

import java.time.DateTimeException;
import java.time.LocalDateTime;

/**
 * Extends from Task, a task with a name and date and time for when the event will be held.
 */
public class Event extends Task {

    protected LocalDateTime at;

    /**
     * Constructor for an event, defaults isDone as false (Marks the new event as undone).
     * @param description this event's name
     * @param at this event's date and time in the format dd/mm/yyyy hhmm
     */
    public Event(String description, String at) {
        super(description);
        try {
            this.at = parseDateTime(at);
        } catch (NumberFormatException ex) {
            Ui.showError("Invalid format! Try dd/mm/yyyy hhmm instead!");
        } catch (DateTimeException ex) {
            Ui.showError(ex.getMessage());
        }
    }

    /**
     * Constructor for an event when reading from state file.
     * @param description this event's name
     * @param isDone boolean true or false if this event is done or undone respectively
     * @param at this event's date and time in the format dd/mm/yyyy hhmm
     */
    public Event(String description, boolean isDone, String at) {
        super(description, isDone);
        this.at = parseDateTime(at);
    }

    private LocalDateTime parseDateTime(String at) throws DateTimeException, NumberFormatException {
        try {
            String[] splited = at.split(" ");
            String[] dateFields = splited[0].split("/");
            int hour = Integer.parseInt(splited[1].substring(0, 2));
            int minute = Integer.parseInt(splited[1].substring(2));
            return LocalDateTime.of(Integer.parseInt(dateFields[2]), Integer.parseInt(dateFields[1]),
                    Integer.parseInt(dateFields[0]), hour, minute);
        } catch (DateTimeException | NumberFormatException ex) {
            throw ex;
        }
    }

    /**
     * Gets string of at field for this event (date and time of when this event will be).
     * @return this event's datetime in the format dd/mm/yyyy hhmm
     */
    public String getStringAt() {
        return at.getDayOfMonth() + "/" + at.getMonthValue() + "/" + at.getYear() + " "
                + String.format("%02d", at.getHour()) + String.format("%02d", at.getMinute());
    }

    /**
     * Sets localDateTime with user input by replacing current DateTime.
     * @param newDateTime String format of new DateTime user wishes to set
     */
    public void setLocalDateTime(String newDateTime) {
        this.at = parseDateTime(newDateTime);
    }

    /**
     * Returns String representation of this event.
     * @return String in format [E] (super class Task's toString) at: (this event's datetime in the format
     *     dd/mm/yyyy hhmm)
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + getStringAt() + ")";
    }
}
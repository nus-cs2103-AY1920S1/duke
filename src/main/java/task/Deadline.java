package task;

import run.Ui;

import java.time.DateTimeException;
import java.time.LocalDateTime;

/**
 * Extends from Task, a task with a name and date and time for when the deadline is due by.
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Constructor for a deadline, defaults isDone as false (Marks the new deadline as undone).
     * @param description this deadline's name
     * @param by this deadline's date and time in the format dd/mm/yyyy hhmm
     */
    public Deadline(String description, String by) {
        super(description);
        try {
            this.by = parseDateTime(by);
        } catch (NumberFormatException ex) {
            Ui.showError("Invalid format! Try dd/mm/yyyy hhmm instead!");
        } catch (DateTimeException ex) {
            Ui.showError(ex.getMessage());
        }
    }

    /**
     * Constructor for a deadline when reading from state file.
     * @param description this deadline's name
     * @param isDone boolean true or false if this deadline is done or undone respectively
     * @param by this deadline's due date and time in the format dd/mm/yyyy hhmm
     */
    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = parseDateTime(by);
    }

    private LocalDateTime parseDateTime(String by) throws DateTimeException, NumberFormatException {
        try {
            String[] splited = by.split(" ");
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
     * Gets string of at field for this deadline (date and time of when this deadline is due by).
     * @return this deadline's datetime in the format dd/mm/yyyy hhmm
     */
    public String getStringBy() {
        return by.getDayOfMonth() + "/" + by.getMonthValue() + "/" + by.getYear() + " "
                + String.format("%02d", by.getHour()) + String.format("%02d", by.getMinute());
    }

    /**
     * Sets localDateTime with user input by replacing current DateTime.
     * @param newDateTime String format of new DateTime user wishes to set
     */
    public void setLocalDateTime(String newDateTime) {
        this.by = parseDateTime(newDateTime);
    }

    /**
     * Returns String representation of this deadline.
     * @return String in format [D] (super class Task's toString) at: (this deadline's datetime in the format
     *     dd/mm/yyyy hhmm)
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getStringBy() + ")";
    }
}
import java.text.SimpleDateFormat;
import java.util.Date;

import java.text.ParseException;

/**
 * Represents a task (something that needs to be done)
 * Parent class of different types of Task
 */
public class Task {
    protected String description;
    protected String subDescription; // For /by, /at
    protected boolean isDone;

    public Task(String description, String subDescription) {
        this.description = description;
        this.subDescription = subDescription;
        this.isDone = false;
    }

    public String getStatusIcon() {
        //return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
        return (isDone? "v" : "x");
    }

    public String getDescription() {
        return description;
    }
    public String getSubDescription() {return subDescription; }

    public void markDone() {
        isDone = true;
    }
    public boolean isDone() { return isDone; }

    //Display "[status-icon] task-description"
    public String toString() {
        return "[" + this.getStatusIcon() + "] " +
                this.description;
    }

    // Mainly for use in saving data to hard disk
    public String getTaskType() { return "?"; }

    // Returns true if description or subdescription contains keyword
    // NOTE: case-sensitive!!!
    public boolean containsKeyword(String keyword) {
        return description.contains(keyword);
    }
}

class ToDo extends Task {
    public ToDo(String description) {
        super(description, null);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
    public String getTaskType() { return "T"; }
}

class Deadline extends Task {
    public Deadline(String description, String by) throws DukeException {
        super(description, by);
        // If deadline /by is of correct format
        if (isValidDateTimeFormat(by)) {
            try {
                SimpleDateFormat displayFormat = new SimpleDateFormat("dd MMMM yyyy, hh:mm a");
                SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy HHmm");
                Date date = inputFormat.parse(by);
                // Output: 2nd of December 2019, 6pm
                by = displayFormat.format(date);
                this.subDescription = by;
            } catch (ParseException e) {
                throw new DukeException("Unable to recognise date-time provided.");
            }
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + subDescription + ")";
    }
    public String getTaskType() { return "D"; }

    public boolean containsKeyword(String keyword) {
        return description.contains(keyword) || subDescription.contains(keyword);
    }

    /**
     * Returns whether description for /by is of
     * recognised date-time format
     * Format: 2/12/2019 1800
     * Which can then be converted to format: 2 December 2019 6:00 pm
     * @param str
     * @return Whether description for /by can be converted
     */    private boolean isValidDateTimeFormat(String str) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyy hhmm");
        try {
            /*str.matches("([1-9]|[1-2][0-9]|3[0-1])/" +
                    "([1-9]|1[0-2])/" +
                    "([0-9]{4})" +
                    "\\s[0-2][0-9]([0-5]{2})")
             */
            inputFormat.parse(str);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

}

class Event extends Task {
    public Event(String description, String at) {
        super(description, at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + subDescription + ")";
    }
    public String getTaskType() { return "E"; }
    public boolean containsKeyword(String keyword) {
        return description.contains(keyword) || subDescription.contains(keyword);
    }

}
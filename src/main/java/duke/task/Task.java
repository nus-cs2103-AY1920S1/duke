package duke.task;

import duke.exception.DukeException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Defines an abstract class of a Task.
 */
public abstract class Task {
    /**
     * The description of the task.
     */
    protected String description;
    /**
     * Whether the task is done.
     */
    protected boolean isDone;
    /**
     * The type of the task, stored as a letter.
     */
    protected String taskType;

    /**
     * Constructor for the task.
     * All tasks are marked as not done by default.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Parses the date if required.
     * Since the dates can have two forms, as specified in the user input, and the form that is specified
     * in the storage file, the method tries both formats, and if both SimpleDateFormat objects cannot
     * successfully parse the string, then a parseException is thrown, and it outputs "ERROR PARSING DATE".
     * When parsing from storage form, we have to remove the ordinal as SimpleDateFormat does not allow for
     * parsing ordinals. The switch case applies the correct ordinal, depending on the day of the month.
     *
     * @param input The date to be parsed as a String.
     * @return A string with the date parsed for display.
     */
    public String parseDate(String input) {
        SimpleDateFormat userInputParser = new SimpleDateFormat("d/MM/yyyy HHmm");
        SimpleDateFormat storageInputParser = new SimpleDateFormat("d 'of' MMMM yyyy',' h'.'mma");
        try {
            Date parsedDate;
            try {
                parsedDate = userInputParser.parse(input);
            } catch (ParseException e) {
                try {
                    String inputWithRemovedOrdinal = input.replaceAll("(?<=\\d)(st|nd|rd|th)", "");
                    parsedDate = storageInputParser.parse(inputWithRemovedOrdinal);
                } catch (ParseException f) {
                    throw f;
                }
            }
            Calendar cal = Calendar.getInstance();
            cal.setTime(parsedDate);
            int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
            if (dayOfMonth < 10 || dayOfMonth > 19) {
                switch (dayOfMonth % 10) {
                case 1:
                    return new SimpleDateFormat("d'st' 'of' MMMM yyyy',' h'.'mma").format(parsedDate);
                case 2:
                    return new SimpleDateFormat("d'nd' 'of' MMMM yyyy',' h'.'mma").format(parsedDate);
                case 3:
                    return new SimpleDateFormat("d'rd' 'of' MMMM yyyy',' h'.'mma").format(parsedDate);
                default:
                    return new SimpleDateFormat("d'th' 'of' MMMM yyyy',' h'.'mma").format(parsedDate);
                }
            } else {
                return new SimpleDateFormat("d'th' 'of' MMMM yyyy',' h'.'mma").format(parsedDate);
            }
        } catch (ParseException e) {
            System.out.println("ERROR PARSING DATE");
            return "";
        }
    }

    /**
     * Getter for the status icon.
     * Represents the boolean either as the tick or cross characters for display.
     *
     * @return A string representation of the isDone boolean
     */
    public String getStatusIcon() {
        return (isDone ? "✓" : "✘");
    }

    /**
     * Getter for the task description.
     *
     * @return A string of the task description
     */
    public String getTaskDescription() {
        return this.description;
    }

    /**
     * Marks a Task as done.
     * We return the task so that we can get its updated status more easily.
     *
     * @return the task that has been marked as done
     * @throws DukeException if there is an error marking the deadline as done
     */
    public abstract Task markAsDone() throws DukeException;


    /**
     * Getter for the type of the task.
     *
     * @return A string containing the type of the task as a string.
     */
    public String getTaskType() {
        return this.taskType;
    }

    /**
     * Gets the task status as a string.
     * The string returned is formatted as specified on the CS2103T website.
     *
     * @return the task status as a string.
     */
    public abstract String getTaskStatus();

    /**
     * Gets the task status a string, formatted for storage in the .txt file.
     * The string returned is formatted for storage as specified on the CS2103T website.
     *
     * @return the task status as a string for storage.
     */
    public abstract String getStoredTaskStatus();
}

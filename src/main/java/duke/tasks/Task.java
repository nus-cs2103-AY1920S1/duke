package duke.tasks;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

/**
 * Represents the tasks which has a description and the
 * completion status of the task.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Create a task object.
     * Extended by Event, Deadline and ToDo.
     *
     * @param description a description given for the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the status of the task. Whether is it completed
     * or incomplete.
     *
     * @return a string that shows the status icon of tick
     *     or cross where tick is completed and cross is incomplete
     */
    public String taskCompletion() {
        String status = getStatusIcon();
        return ("[" + status + "] " + description);
    }

    /**
     * Change the status of a task from incomplete to completed.
     */
    //Change task to completed;
    public void completed() {
        this.isDone = true;
    }

    /**
     * Check if the task is completed or not.
     *
     * @return the completion status of the task
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Gives either a tick or cross icon.
     *
     * @return a tick of cross icon
     */
    //get the tick or cross
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    //save to file
    public String save() {
        return "Saving";
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Converts a string to a date object.
     *
     * @param dateTime the date and time that needs to be converted
     *                 to a date object
     * @return a date object from the string given
     */
    public static Date dateTimeConversion(String dateTime) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyy HHmm");
        String dateInString = dateTime;
        try {
            Date date = formatter.parse(dateInString);
            return date;
        } catch (ParseException e) {
            System.out.println("Not valid date and time");
            Date date = null;
            return date;
        }
    }

    @Override
    public String toString() {
        String status = getStatusIcon();
        return ("[" + status + "] " + description);
    }
}
package duke.task;

import duke.exception.InvalidDateTimeException;
import duke.exception.NullDateException;
import duke.extension.Reminder;
import duke.formatter.DateFormatter;
import duke.parser.DateParser;

import java.util.Date;
import java.util.Optional;

/**
 * This is the task item to be added into the list of tasks. The <code>Task</code> abstract class provides an
 * abstraction over {@link Todo}, {@link Deadline}, {@link Event} items.
 */
public class Task {

    /**
     * This is the description of the task.
     */
    protected String description;
    /**
     * This is the container for the date field of the task. The container may be empty if it is a {@link Todo} task.
     */
    protected Optional<Date> taskDate = Optional.empty();
    /**
     * This is the reminder set for the task.
     */
    protected Reminder reminder;


    /**
     * This is used to indicate whether the task is done.
     */
    protected boolean isDone;

    /**
     * Constructs a new task with description.
     * @param description the description for the task
     */
    public Task(String description) {
        this.description = description;
        //reminder is created with no timer set
        this.reminder = new Reminder(this.toString());
    }

    /**
     * Constructs a new task with description and date field for the task.
     * @param description the description for the task
     * @param taskDate the date field for the task
     */
    public Task(String description, Date taskDate) {
        this.description = description;
        this.taskDate = Optional.ofNullable(taskDate);
        //reminder is created with no timer set
        this.reminder = new Reminder(this.toString());
    }

    /**
     * Gets the status icon of the done status of the task.
     * @return a status icon of the done status of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Displays a string representation of the reminder.
     * @return a string representation of the reminder
     */
    public String displayReminderIfPresent() {
        return reminder.toString();
    }

    /**
     * Sets a reminder for the task for the specified date.
     * @param date the date of the reminder
     */
    public void setReminder(Date date) {
        assert (date != null);
        reminder.setTimer(date);
    }

    /**
     * Checks if the task's description contain the specified sequence of <code>char</code> values in the keyword.
     * @param keyword the specified sequence of <code>char</code> values
     * @return <code>true</code> if task's description contains <code>query</code>,<code>false</code> otherwise
     */
    public boolean contains(String keyword) {
        return description.contains(keyword);
    }

    /**
     * Gets the task date for the task if present.
     * @return the task date for the task if present
     * @throws NullDateException if the task date is absent
     */
    public Date getTaskDate() throws NullDateException {
        return taskDate.orElseThrow(NullDateException::new);
    }

    /**
     * Displays a string representation of the task date if present.
     * @return a string representation of the task date if present, and an empty string otherwise.
     */
    public String displayTaskDateIfPresent() {
        if (taskDate.isPresent()) {
            return DateFormatter.format(taskDate.get()).substring(0,10);
        } else {
            return "";
        }
    }

    /**
     * Encodes a date field of the task for storage.
     * @param optionalDate the optional date to encode
     * @return a string representation of the date field if present and <code>"null"</code> otherwise.
     */
    public String encodeOptionalDate(Optional<Date> optionalDate) {
        if (optionalDate.isPresent()) {
            return DateFormatter.format(optionalDate.get());
        } else {
            return "null";
        }
    }

    /**
     * Encodes the task into a format for storage purposes.
     * @return a string representation of the encoded task
     */
    public String encode() {
        StringBuilder outputBuilder = new StringBuilder("" + isDone);
        outputBuilder.append(',');
        outputBuilder.append(encodeOptionalDate(reminder.getOptionalDate()));
        outputBuilder.append(',');
        outputBuilder.append(description);
        outputBuilder.append(',');
        outputBuilder.append(encodeOptionalDate(taskDate));
        return outputBuilder.toString();
    }

    /**
     * Shows full information for the task with its task description, date, status and the reminder date if present.
     * @return a string representation of the full information of the task with its corresponding description, date,
     *      status and the reminder date if present
     */
    public String showFullInformation() {
        StringBuilder outputBuilder = new StringBuilder();
        outputBuilder.append(toString());
        outputBuilder.append("\n");
        outputBuilder.append("     ");
        outputBuilder.append(displayReminderIfPresent());
        outputBuilder.append("\n");
        return outputBuilder.toString();
    }

    /**
     * Returns a string representation of the task. The string representation consist of the status icon with the
     * task description.
     * @return a string representation of the task
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Mark the task as done if the string passed in is equal to <code>"true"</code>.
     * @param isDone a string representation of whether the task is done.
     */
    public void markAsDoneIfTrue(String isDone) {
        this.isDone = isDone.equals("true");
    }

    /**
     * Mark the task as done if the boolean value passed in is <code>true</code>.
     * @param isDone the boolean value of whether the task is done
     */
    public void markAsDoneIfTrue(Boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Sets the reminder for the task at the specified date if a string representation of the remainder date is valid.
     * @param reminderDate the string representation to set the reminder for the date
     * @throws InvalidDateTimeException if the string representation of the date passed in has a invalid format
     */
    public void setReminderIfPresent(String reminderDate) throws InvalidDateTimeException {
        boolean isPresent = !reminderDate.equals("null");
        if (isPresent) {
            Date date = DateParser.parse(reminderDate);
            reminder.setTimer(date);
        }
    }

}

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

    protected Optional<Date> taskDate = Optional.empty();

    protected Optional<Date> reminderDate = Optional.empty();


    /**
     * This is used to indicate whether the task is done.
     */
    protected boolean isDone;

    /**
     * Constructs a new task with description that is not done.
     * @param description the description for the task
     */
    public Task(String description) {
        this.description = description;
    }

    public Task(String description, Date taskDate) {
        this.description = description;
        this.taskDate = Optional.ofNullable(taskDate);
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

    public String displayReminderIfPresent() {
        if(reminderDate.isPresent()) {
            return "Reminder set to: " + reminderDate.get().toString();
        } else {
            return "";
        }
    }

    public void setReminder(Date date) {
        reminderDate = Optional.ofNullable(date);
        new Reminder(this, date);
    }

    public void clearReminder() {
        reminderDate = Optional.empty();
    }

    /**
     * Checks if the task's description contain the specified sequence of <code>char</code> values in the keyword.
     * @param keyword the specified sequence of <code>char</code> values
     * @return <code>true</code> if task's description contains <code>query</code>,<code>false</code> otherwise
     */
    public boolean contains(String keyword) {
        return description.contains(keyword);
    }

    public Date getTaskDate() throws NullDateException {
        return taskDate.orElseThrow(NullDateException::new);
    }

    public String displayTaskDateIfPresent() {
        if(taskDate.isPresent()) {
            return DateFormatter.format(taskDate.get()).substring(0,10);
        } else {
            return "";
        }
    }

    public String encodeOptionalDate(Optional<Date> optionalDate) {
        if(optionalDate.isPresent()) {
            return DateFormatter.format(optionalDate.get());
        } else {
           return "null";
        }
    }

    public String encode() {
        StringBuilder outputBuilder = new StringBuilder("" + isDone);
        outputBuilder.append(',');
        outputBuilder.append(encodeOptionalDate(reminderDate));
        outputBuilder.append(',');
        outputBuilder.append(description);
        outputBuilder.append(',');
        outputBuilder.append(encodeOptionalDate(taskDate));
        return outputBuilder.toString();
    }

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

    public void markAsDoneIfTrue(String isDone) {
        this.isDone = isDone.equals("true");
    }

    public void markAsDoneIfTrue(Boolean isDone) {
        this.isDone = isDone;
    }

    public void setReminderIfPresent(String reminderDate) throws InvalidDateTimeException {
        boolean isPresent = !reminderDate.equals("null");
        if(isPresent) {
            Date date = DateParser.parse(reminderDate);
            this.reminderDate = Optional.of(date);
            new Reminder(this, date);
        }
    }
}

package tasks;

import java.util.Date;

/**
 * Represents a task.
 *
 * @author Michelle Yong
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected Date date;
    protected String type = "";
    protected String priority;
    protected final int DONE = 1;
    protected final int NOT_DONE = 0;
    protected final String DONE_ICON = "\u2713";
    protected final String NOT_DONE_ICON = "\u2718";

    /**
     * Creates a task with description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Gets the date of the task.
     *
     * @return The date of the task.
     */
    public Date getDate() {
        return this.date;
    }

    /**
     * Gets the type of the task.
     *
     * @return The type of the task.
     */
    public String getType() {
        return this.type;
    }

    /**
     * Gets if the task is marked done.
     *
     * @return True if task is mark as done.
     */
    public boolean getIsDone() {
        return this.isDone;
    }


    /**
     * Gets the status icon of the task.
     * Tick if it is done, cross otherwise.
     *
     * @return The status icon of the task.
     */
    public String getStatusIcon() {
        return isDone ? DONE_ICON : NOT_DONE_ICON;
    }

    /**
     * Gets the status number of the task.
     * 1 if it is done, 0 otherwise.
     *
     * @return The status number of the task.
     */
    public int getStatusNum() {
        return isDone ? DONE : NOT_DONE;
    }

    public String getPriority() {
        return this.priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    /**
     * Mark the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
        assert isDone;
    }

    /**
     * Checks if the task description contains the keyword
     *
     * @param keyword The word to be found.
     * @return True if the task description contains the keyword.
     */
    public boolean hasKeyword(String keyword) {
        return description.contains(keyword);
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
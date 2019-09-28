package tasks;

import java.util.Date;

/**
 * Represents a task.
 *
 * @author Michelle Yong
 */
public class Task {
    protected boolean isDone;
    protected final int DONE = 1;
    protected final int NOT_DONE = 0;
    protected final String DONE_ICON = "\u2713";
    protected final String NOT_DONE_ICON = "\u2718";
    protected final String LOW_PRIORITY = "low";
    protected final String MEDIUM_PRIORITY = "medium";
    protected final String HIGH_PRIORITY = "high";
    protected final String LOW_PRIORITY_ICON = "\u263A";
    protected final String HIGH_PRIORITY_ICON = "\u2620";
    protected String description;
    protected Date date;
    protected String type = "";
    protected String priority = MEDIUM_PRIORITY;

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

    /**
     * Gets the priority of the task.
     *
     * @return The priority of the task, low, medium or high.
     */
    public String getPriority() {
        return this.priority;
    }

    /**
     * Sets the priority level of the tasks.
     *
     * @param priority The priority level to be set.
     */
    public void setPriority(String priority) {
        this.priority = priority.toLowerCase();
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
        return description.toLowerCase().contains(keyword.toLowerCase());
    }

    @Override
    public String toString() {
        String task = "[" + this.getStatusIcon() + "] " + this.getDescription();
        if (this.getPriority().equals(HIGH_PRIORITY)) {
            task += (" " + HIGH_PRIORITY_ICON);
        } else if (this.getPriority().equals(LOW_PRIORITY)) {
            task += (" " +  LOW_PRIORITY_ICON);
        }
        return task;
    }
}
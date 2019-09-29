//Solution below adapted from https://nus-cs2103-ay1920s1.github.io/website/schedule/week2/project.html
package seedu.duke.tasks;

/**
 * Parent class of all the tasks.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor to create a task with a description.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the status icon of the task to see if the task is done or not.
     *
     * @return "+" if task is done, "-" if it is not.
     */
    public String getStatusIcon() {
        return (isDone ? "+" : "-"); //return + for done and nothing for not done
    }

    /**
     * Sets itself as done.
     */
    public void setDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + " " + description;
    }
}
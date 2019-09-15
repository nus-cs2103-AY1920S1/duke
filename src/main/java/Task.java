// @@author CS2103/T Software Engineering AY1920S1
// Referenced from https://nus-cs2103-ay1920s1.github.io/website/schedule/week2/project.html
// with minor modifications 

/**
 * Superclass from which all list items inherit.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with a description.
     * 
     * @param description A String representation of this Task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the status icon of this Task.
     * 
     * @return A String containing the status icon of this Task
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Marks a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns a String representation of this Task.
     * 
     * @return A String representation of this Task.
     */
    public String toString() {
        return "["
            + getStatusIcon()
            + "] "
            + description;
    }
    
    /**
     * Returns the description of this Task.
     * 
     * @return Description of this Task.
     */
    public String getDescription() {
        return this.description;
    }
}
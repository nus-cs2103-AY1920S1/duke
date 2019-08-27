package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a Task object.
     *
     * @param description Description of task.
     */
    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return Status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone(){
        isDone = true;
    }

    /**
     * Returns the state of the task.
     *
     * @return State of task.
     */
    public boolean getIsDone(){
        return isDone;
    }

    /**
     * Returns the description of task.
     *
     * @return Description of task.
     */
    public String getDescription(){
        return description;
    }

    @Override
    public String toString(){
        return "[" + getStatusIcon() + "] " + description;
    }
}

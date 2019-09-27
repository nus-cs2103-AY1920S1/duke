package duke.task;

/**
 * Represents a Task in the list.
 */
public class Task {

    protected String desc;
    protected boolean isDone;
    protected String doneSymbol;

    /**
     * An empty constructor that creates a Task object.
     */
    public Task() {

    }

    /**
     * Creates a Task object with the specified description.
     * @param desc Description of the task.
     */
    public Task(String desc) {
        this.desc = desc;
        this.isDone = false;
        setDoneSymbol();
    }

    /**
     * Creates a Task object with the specified description and isDone status.
     * @param desc Description of the task.
     * @param isDone if true, the task is done.
     */
    public Task(String desc, boolean isDone) {
        this.desc = desc;
        this.isDone = isDone;
        setDoneSymbol();
    }

    public String getDesc() {
        return this.desc;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    protected String getDoneSymbol() {
        return this.isDone ? "\u2713" : "x"; // returns check mark or cross
    }

    protected void setDoneSymbol() {
        this.doneSymbol = isDone ? "\u2713" : "x"; // returns check mark or cross
    }

    /**
     * Marks a task as done, and changes the doneSymbol to a check mark.
     */
    public void markAsDone() {
        this.isDone = true;
        setDoneSymbol();
    }

}

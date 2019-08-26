/*
A child class of Object which contains the description of the Task and whether the Task has been done.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    //for the purpose of text-ui-testing
    /*
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "?");
    } */

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public void markAsDone() throws DukeException {
        if (this.isDone == true) {
            throw new DukeException("This task has already been done!");
        } else {
            this.isDone = true;
        }
    }

    public void recordDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /*
    This method returns an empty String as it will be overridden in its child classes for more specific format of each type of task.
     */
    public String format() {
        return "wrong format method used";
    }
}

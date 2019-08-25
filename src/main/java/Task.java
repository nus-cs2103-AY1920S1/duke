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

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}

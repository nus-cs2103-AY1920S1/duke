package duke.task;

public class Task {
    public String type; //Todo, Deadline, Event
    public String task;
    private boolean isDone;

    /**
     * Constructor for Task from user input.
     */
    public Task(String type, String task) {
        this.type = type;
        this.task = task;
        this.isDone = false;
    }

    /**
     * Constructor for Task from hard drive storage.
     */
    public Task(boolean isDone, String type, String task) {
        this.isDone = isDone;
        this.type = type;
        this.task = task;
    }

    public void markIsDone() {
        this.isDone = true;
    }

    public void undoMarkDone() {
        this.isDone = this.isDone && false;
    }

    public boolean checkIsDone() {
        return this.isDone;
    }

    /**
     * Displays tick or cross based on whether the task is done or not.
     */
    public String displayIsDone() {
        return this.isDone
                ? "[" + "\u2713" + "] " //done
                : "[" + "\u2718" + "] "; //not done
    }

    public String getTask() {
        return this.task;
    }

    private String isDoneInt(boolean isDone) {
        return isDone ? "1" : "0";
    }

    /**
     * Saves the Task in the format that can easily be retrieved when the file
     * is opened next time.
     *
     * @return Task decoded into a format suitable for saving on hard disc.
     */
    public String saveTask() {
        return String.format(" | " + isDoneInt(this.isDone)
                + " | " + this.task);
    }

    @Override
    public String toString() {
        return String.format(this.displayIsDone() + this.task);
    }
}
package duke.task;

public abstract class Task {

    protected String description;
    protected boolean taskIsDone;
    protected int taskIsDoneState;

    public Task(String description) {
        this.description = description;
        this.taskIsDone = false;
        this.taskIsDoneState = 0;
    }

    public void taskComplete() {
        taskIsDone = true;
        taskIsDoneState = 1;
    }

    public abstract String toSaveFormat();

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        String output;
        if (taskIsDone) {
            output = "Complete";
        } else {
            output = "Incomplete";
        }
        output += " " + description;
        return output;
    }
}
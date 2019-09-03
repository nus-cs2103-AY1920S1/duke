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

    @Override
    public String toString() {
        String output;
        if (taskIsDone == true) {
            output = "[✓]";
        } else {
            output = "[✗]";
        }
        output += " " + description;
        return output;
    }
}
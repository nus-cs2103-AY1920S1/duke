/**
 * Represents a Task in the list.
 */
public class Task {

    private String desc;
    private int taskNo;
    private boolean isDone;
    private String doneSymbol;

    public Task(String desc, int taskNo) {
        this.desc = desc;
        this.taskNo = taskNo;
        this.isDone = false;
        setDoneSymbol();
    }

    public String getTask() {
        return this.desc;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public void setTask(String desc) {
        this.desc = desc;
    }

    // marks a task as done, changes the done symbol to check mark
    public void markAsDone() {
        this.isDone = true;
        setDoneSymbol();
    }

    private void setDoneSymbol() {
        this.doneSymbol = isDone ? "✓" : "x";
    }

    @Override
    public String toString() {
        return taskNo + "." + "[" + doneSymbol + "] " + desc;
    }

}

/**
 * Represents a Task in the list.
 */
public class Task {

    protected String desc;
    //protected int taskNo;
    protected boolean isDone;
    protected String doneSymbol;

    public Task(String desc) {
        this.desc = desc;
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

    protected String getDoneSymbol() {
        return this.isDone ? "✓" : "x";
    }

    protected void setDoneSymbol() {
        this.doneSymbol = isDone ? "✓" : "x";
    }

    // marks a task as done, changes the done symbol to check mark
    protected void markAsDone() {
        this.isDone = true;
        setDoneSymbol();
    }

//    @Override
//    public String toString() {
//        return taskNo + "." + "[" + doneSymbol + "] " + desc;
//    }

}

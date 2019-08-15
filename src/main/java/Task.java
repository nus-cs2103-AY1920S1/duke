public class Task {

    protected String taskName;
    protected boolean isDone;

    public Task(String name) {
        this.isDone = false;
        this.taskName = name;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", this.getType() ,isDone ? "\u2713": "\u2718", taskName );
    }

    public void done() {
        this.isDone = true;
    }

    public String getType() {
    	return " ";
    }



}

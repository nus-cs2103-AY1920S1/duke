public abstract class Task {
    protected String description;
    protected boolean isDone;
    public static int totalTasks = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void completeTask(){
        isDone = true;
    }

    @Override
    public String toString(){
        return "[" + getStatusIcon() + "]" + " " + description;
    }

}

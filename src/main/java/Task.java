public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String desc) {
        this.description = desc;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        isDone = true;
    }

    public String getTask(){
        String output = "[" + getStatusIcon() + "] " + description;
        return output;
    }

    @Override
    public String toString(){
        return getTask();
    }
}

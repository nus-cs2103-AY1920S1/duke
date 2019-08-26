public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public void markAsDone(){
        this.isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public abstract String getFormatToFile();

    @Override
    public String toString(){
//       System.out.println("debug: " + getStatusIcon());
        return "["+ getStatusIcon() + "] " + description;
    }
}
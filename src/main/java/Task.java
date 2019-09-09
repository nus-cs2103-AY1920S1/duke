public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void setDone() {
        this.isDone = true;
    }
    
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
    
    public String toStringDone() {
        return "Nice, I've marked this item as done\n\t\t[" + this.getStatusIcon() + "] " + this.description;
    }
    
    //...
}


// @@author CS2103/T Software Engineering AY1920S1
// Referenced from https://nus-cs2103-ay1920s1.github.io/website/schedule/week2/project.html
// with minor modifications 

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

    public void markAsDone() {
        this.isDone = true;
    }

    public String toString() {
        return "["
            + getStatusIcon()
            + "] "
            + description;
    }
    
    public String getDescription() {
        return this.description;
    }
}
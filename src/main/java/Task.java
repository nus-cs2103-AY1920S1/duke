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

    public String getDescription() {
        return description;
    }

    public void markAsDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        String output = "[" + this.getStatusIcon() + "] " + this.getDescription();
        return output;
    }

    public String toTextFileString() {
        String status = "";
        if (this.isDone == true) {
            status = "1|";
        } else {
            status = "0|";
        }
        String output = status + this.getDescription();
        return output;
    }
}

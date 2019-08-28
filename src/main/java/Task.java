public class Task {
    private boolean isDone;
    protected String description;
    protected String timeLabel;
    protected String wordLabel;

    public Task(String description) {
        this.isDone = false;
        this.description = description;
    }

    public Task(String description, String timeLabel) { //Requires both type of parameterised constructors
        this.isDone = false;
        this.description = description;
        this.timeLabel = timeLabel;
    }

    public String getLabel() {
        return wordLabel;
    }

    public String getTimeLabel() {
        if (this.timeLabel != null) {
            return timeLabel;
        } else {
            return "";
        }
    }

    public int getInfo() {
        return this.isDone ? 1 : 0;
    }

    public String getDescription() {
        return this.description;
    }


    public void mark() {
        if (!isDone)
            this.isDone = true;
    } //update status of task if completed

    protected String getStatusIcon() {
        return (isDone ? "[\u2713] " : "[\u2718] "); //return tick or X symbols
    }

    public String toString() {
        return this.getStatusIcon() + this.description;
    }
}
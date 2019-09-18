public class Task {
    protected String description;
    boolean isDone; // deliberate, else need overload with method for isDone
    protected String type;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.type = "task";
    }

    public Task(boolean isDone, String description) {
        this.description = description;
        this.isDone = isDone;
        this.type = "task";
    }

    public String getDescription() {
        return this.description;
    }

    public String getType() {
        return this.type;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void changeDesc(String desc) {
        this.description = desc;
    }

    public String toString() {
        return (isDone ? "[\u2713] " + this.description : "[\u2718] " + this.description); //return tick or X symbols
    }
}

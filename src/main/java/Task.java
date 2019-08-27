public class Task {
    protected String description;
    protected String subDescription; // For /by, /at
    protected boolean isDone;

    public Task(String description, String subDescription) {
        this.description = description;
        this.subDescription = subDescription;
        this.isDone = false;
    }

    public String getStatusIcon() {
        //return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
        return (isDone? "v" : "x");
    }

    public String getDescription() {
        return description;
    }
    public String getSubDescription() {return subDescription; }

    public void markDone() {
        isDone = true;
    }
    public boolean isDone() { return isDone; }

    //Display "[status-icon] task-description"
    public String toString() {
        return "[" + this.getStatusIcon() + "] " +
                this.description;
    }

    // Mainly for use in saving data to hard disk
    public String getTaskType() { return "?"; }
}

class ToDo extends Task {
    public ToDo(String description) {
        super(description, null);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
    public String getTaskType() { return "T"; }
}

class Deadline extends Task {
    public Deadline(String description, String by) {
        super(description, by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + subDescription + ")";
    }
    public String getTaskType() { return "D"; }

}

class Event extends Task {
    public Event(String description, String at) {
        super(description, at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + subDescription + ")";
    }
    public String getTaskType() { return "E"; }

}
public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description.trim();
        this.isDone = false;
    }

    protected char getStatusIcon() {
        return this.isDone ? '\u2713' : '\u2718'; //return tick or X symbols
    }

    public void setDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " +
                this.description;
    }
}

class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by.trim();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")\n";
    }
}

class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at.trim();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")\n";
    }
}

class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() + '\n';
    }
}
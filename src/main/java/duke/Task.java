package duke;

abstract public class Task {
    protected String description;
    protected boolean done;
    public Task(String d) {
        description = d;
        done = false;
    }
    public char getStatusChar() {
        return done ? '✓' : '✗';
    }
    public String getDescription() {
        return description;
    }
    public void markDone() throws DukeException {
        if(done) {
            throw new DukeException("Task is already done!");
        }
        done = true;
    }
    abstract public String toString();
}

class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    // Invalid task throws DukeException
    public Task(String action, int size) throws DukeException {
        if (size == 1) {
            throw new DukeException("    ☹ OOPS!!! The description of a " + action + " cannot be empty.");
        }
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String toSave() {
        return (isDone ? "1" : "0") + " | " + this.description;
    }

}

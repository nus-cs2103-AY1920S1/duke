public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) throws DukeException {
        if (description.equals("")) {
            throw new DukeException("The description of " + this.getTypeNameWithQuantifier() + " cannot be empty.");
        }
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getDescription() {
        return this.description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    protected abstract String getTypeNameWithQuantifier();

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}

public abstract class Task {
    String description;
    boolean isDone;

    public Task (String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markDone() throws DukeException {
        if(isDone) {
            throw new DukeException("Task is already done!");
        } else {
            this.isDone = true;
        }
    }

    public abstract String toString();
}
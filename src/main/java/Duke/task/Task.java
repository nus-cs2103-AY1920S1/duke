package Duke.task;
import Duke.exception.DukeException;

public abstract class Task {
    protected String description;
    protected Boolean done;

    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    public String getDescription() {
        return this.description;
    }

    public void doTask() throws DukeException{
        if (this.done) {
            throw new DukeException("The task specified has already been done.");
        } else {
            this.done = true;
        }
    }

    public void markDone() {
        this.done = true;
    }

    public String getStatusIcon() {
        return done ? "[\u2713]" : "[\u2718]";
    }

    public abstract String formatToWrite();

    @Override
    public String toString() {
       return String.format("%s %s", this.getStatusIcon(), this.description);
    }
}

package duke.tasks;

/**
 * Note task.
 */
public class Note extends Task {

    /**
     * Initialises Note.
     *
     * @param task task
     */
    public Note(String task) {
        super(task);
    }

    @Override
    public String toString() {
        return String.format("[N] %s", this.task);
    }
}
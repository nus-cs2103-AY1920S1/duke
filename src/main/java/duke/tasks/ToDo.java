package duke.tasks;

/**
 * To Do task are tasks without any date/time attached to it.
 */
public class ToDo extends Task {

    /**
     * Initialises To Do with specified task.
     *
     * @param task task
     */
    public ToDo(String task) {
        super(task);
    }

    /**
     * Initialises To Do with specified task and its status.
     *
     * @param task task
     * @param done done
     */
    public ToDo(String task, Boolean done) {
        super(task, done);
    }

    @Override
    public String toString() {
        String iconForDone = done ? "\u2713" : "\u2718";
        return String.format("[T][%s] %s", iconForDone, this.task);
    }
}
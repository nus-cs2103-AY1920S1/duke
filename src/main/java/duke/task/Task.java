package duke.task; 

public abstract class Task {
    String name;
    boolean isDone;

    /**
     * Constructor.
     * @param name String
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Mark task as done.
     */
    public void markDone() {
        this.isDone = true;
    }
}

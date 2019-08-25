package duke.task; 

import java.util.Optional;

public abstract class Task {
    String name;
    TaskType type;
    Optional<String> time;
    boolean isDone;

    /**
     * Constructor.
     * @param name String
     */
    public Task(String name, Optional<String> time, TaskType type, boolean isDone) {
        this.name = name;
        this.time = time;
        this.type = type;
        this.isDone = isDone;
    }

    /**
     * Mark task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Get name.
     * @return String
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get time.
     * @return Optional<String>
     */
    public Optional<String> getTime() {
        return this.time;
    }

    /**
     * Get type.
     * @return TaskType
     */
    public TaskType getType() {
        return this.type;
    }

    /**
     * Get isDone.
     * @return boolean
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Get the status.
     * @return String
     */
    String getStatus() {
        if (this.isDone) {
            return "[✓]";
        } else {
            return "[✗]";
        }
    }
}

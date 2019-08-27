import java.io.Serializable;

/**
 * An abstract class representing a task that can be recorded by Duke. Contains a description and a completion status.
 */
public abstract class Task implements Serializable {
    static protected final char UNICODE_TICK = (char) 0x2713;
    static protected final char UNICODE_CROSS = (char) 0x2718;
    static protected final String TO_STRING_FORMAT = "[%c][%c] %s";

    protected String description;
    protected boolean isDone;

    /**
     * @return A copy of this <code>Task</code>, but with status marked as done
     */
    public abstract Task getTaskMarkedAsDone();
    
    /**
     * @return A copy of this <code>Task</code>, but with status marked as undone
     */
    public abstract Task getTaskMarkedUndone();

    /**
     * @return a <code>char</code> for a tick if this <code>Task</code> is done and a cross otherwise
     */
    protected char getStatusIcon() {
        return isDone ? UNICODE_TICK : UNICODE_CROSS;
    }
}

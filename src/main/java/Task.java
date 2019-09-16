import java.io.Serializable;

/**
 * An abstract class representing a task that can be recorded by Duke. Contains a description and a completion status.
 */
public abstract class Task implements Serializable {
    private static final long serialVersionUID = 122462L;
    
    protected static final char UNICODE_TICK = (char) 0x2713;
    protected static final char UNICODE_CROSS = (char) 0x2718;
    protected static final String TO_STRING_FORMAT = "[%c][%c] %s";

    protected String description;
    protected boolean isDone;

    /**
     * Returns a copy of this <code>Task</code>, but with status marked as done.
     * 
     * @return A copy of this <code>Task</code>, but with status marked as done
     */
    public abstract Task getTaskMarkedAsDone();
    
    /**
     * Returns a copy of this <code>Task</code>, but with status marked as undone.
     * 
     * @return A copy of this <code>Task</code>, but with status marked as undone
     */
    public abstract Task getTaskMarkedUndone();

    /**
     * Returns the unicode icon for whether the <code>Task</code> is done.
     * 
     * @return a <code>char</code> for a tick if this <code>Task</code> is done and a cross otherwise
     */
    protected char getStatusIcon() {
        return isDone ? UNICODE_TICK : UNICODE_CROSS;
    }

    /**
     * Returns whether the description contains the <code>searchTerm</code>.
     * 
     * @return <code>true</code> if the description contains the <code>searchTerm</code> 
     *     and <code>false</code> otherwise
     */
    protected boolean descriptionContainsTerm(String searchTerm) {
        assert description != null : "description not set";
        assert searchTerm != null : "searchTerm is null";
        
        return description.contains(searchTerm);
    }
}

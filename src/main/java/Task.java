import java.io.Serializable;

public abstract class Task implements Serializable {
    static protected final char UNICODE_TICK = (char) 0x2713;
    static protected final char UNICODE_CROSS = (char) 0x2718;
    static protected final String TO_STRING_FORMAT = "[%c][%c] %s";

    protected String description;
    protected boolean isDone;

    //Returns a copy of this task but with its completion status marked as done
    public abstract Task getTaskMarkedAsDone();
    
    //Returns a copy of this task but with its completion status marked as undone
    public abstract Task getTaskMarkedUndone();

    //Returns the character marking denoting a task as either done or undone
    protected char getStatusIcon() {
        return isDone ? UNICODE_TICK : UNICODE_CROSS;
    }
}

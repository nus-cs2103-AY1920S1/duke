/**
 * A class representing a basic <code>Task</code>.
 */
public class ToDoTask extends Task {
    private static final long serialVersionUID = 137301L;
    
    /**
     * Creates an instance of a <code>ToDoTask</code>. The completion status is set to <code>false</code> by default.
     * 
     * @param description The description of this <code>Task</code>
     */
    public ToDoTask(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Creates an instance of a <code>ToDoTask</code>. Allows the caller to set its completion status.
     * 
     * @param description The description of this <code>Task</code>
     * @param isDone The completion status of the <code>Task</code>
     */
    public ToDoTask(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }
    
    @Override
    /**
     * Returns a copy of this <code>Task</code> but with its completion status marked as done.
     * 
     * @return A copy of this <code>Task</code> but with its completion status marked as done
     */
    public Task getTaskMarkedAsDone() {
        return new ToDoTask(description, true);
    }

    /**
     * Returns a copy of this <code>Task</code> but with its completion status marked as done.
     * 
     * @return a copy of this <code>Task</code> but with its completion status marked as done
     */
    @Override
    public Task getTaskMarkedUndone() {
        return new ToDoTask(description, false);
    }

    /**
     * Returns the <code>String</code> representation of this <code>Task</code>.
     * 
     * @return The <code>String</code> representation of this <code>Task</code>, 
     *     containing the type of <code>Task</code>, completion status and description
     */
    @Override
    public String toString() {
        return String.format(TO_STRING_FORMAT, 'T', this.getStatusIcon(), this.description);
    }
}

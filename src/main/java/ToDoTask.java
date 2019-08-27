/**
 * A class representing a basic <code>Task</code>.
 */
public class ToDoTask extends Task {
    /**
     * Creates an instance of a <code>ToDoTask</code>. The completion status is set to <code>false</code> by default.
     * 
     * @param description The description of this <code>Task</code>
     */
    public ToDoTask (String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Creates an instance of a <code>ToDoTask</code>. Allows the caller to set its completion status.
     * 
     * @param description The description of this <code>Task</code>
     * @param isDone The completion status of the <code>Task</code>
     */
    public ToDoTask (String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }
    
    @Override
    //Returns a copy of this task but with its completion status marked as done
    public Task getTaskMarkedAsDone() {
        return new ToDoTask(description, true);
    }

    @Override
    //Returns a copy of this task but with its completion status marked as undone
    public Task getTaskMarkedUndone() {
        return new ToDoTask(description, false);
    }

    /**
     * @return The <code>String</code> representation of this <code>Task</code>, containing the type of <code>Task</code>,
     * completion status and description
     */
    @Override
    public String toString() {
        return String.format(TO_STRING_FORMAT, 'T', this.getStatusIcon(), this.description);
    }
}

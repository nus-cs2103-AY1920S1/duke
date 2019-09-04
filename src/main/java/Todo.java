/**
 * Represents a task without any specific date/time.
 * A <code>Todo</code> object corresponds to a task represented by
 * one String e.g., <code>"visit new theme park"</code>
 */
public class Todo extends Task {

    /**
     * Constructs a <code>Todo</code> with a task description.
     *
     * @param description Description of task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns condensed description of specified todo task.
     *
     * @return Condensed description.
     */
    public String formatString() {
        return String.format("T | %d | %s", super.isDone ? 1 : 0, super.description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
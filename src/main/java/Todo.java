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
     * Constructs a <code>Todo</code> with task done indicator and task description.
     *
     * @param indicator Done indication of task.
     * @param description Description of task.
     */
    public Todo(String indicator, String description) {
        super(description);

        if (indicator.equals("1")) {
            this.setDone();
        } else {
            assert indicator.equals("0") : "String indicator should be 0";
        }
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
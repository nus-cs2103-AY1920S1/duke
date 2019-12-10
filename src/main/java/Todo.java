/**
 * This is a class for todo tasks.
 * @author Choong Yong Xin
 */

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    /**
     * Returns string for task display.
     *
     * @return Display string
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns string for file writing.
     *
     * @return String to be saved.
     */
    @Override
    public String stringForAppend() {
        return "T | " + super.getStatusIcon() + " | " + description;
    }
}
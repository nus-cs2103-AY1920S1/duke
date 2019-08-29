package duke.task;
/**
 * one of the tasks used to record things need to do
 */
public class Todo extends Task {
    /**
     * A constructor to write in content of a todo task.
     * @param description content of the task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * the Overriding method
     * @return String that in todo format
     */
    @Override
    public String toString() {
        return "T -- " + super.toString();
    }
}

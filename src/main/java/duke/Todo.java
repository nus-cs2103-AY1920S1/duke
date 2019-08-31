/**
 * Represents a to-do, but unlike other Tasks, this does not have a date/time limit.
 */
class Todo extends Task {
    /**
     * Constructor for Todo.
     * 
     * @param task The given task by the user.
     */
    public Todo(String task) {
        super(task);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
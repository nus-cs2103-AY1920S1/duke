package duke.task;

/**
 * Represents a type of task known as Todo.
 */
public class Todo extends Task {

    /**
     * Initialises a Todo.
     * A Todo consists of the task itself.
     *
     * @param item name of the task.
     */
    // the Todo subclass has one constructor
    public Todo(String item) {
        super(item);
    }

    /**
     * Prints a Todo as a string to the user in a specific format.
     *
     * @return Todo.
     */
    @Override
    // overrides toString method in Task
    public String toString() {
        if (isDone) {
            return "[T][" + "/" + "] " + super.toString();
        } else {
            return "[T][" + "x" + "] " + super.toString();
        }
    }

    /**
     * Saves a Todo as a string to the file in a specific format.
     *
     * @return Todo.
     */
    @Override
    // overrides saveTask method in Task
    public String saveTask() {
        if (isDone) {
            return "T" + " | " + "1" + " | " + super.toString() + "\n";
        } else {
            return "T" + " | " + "0" + " | " + super.toString() + "\n";
        }
    }

}

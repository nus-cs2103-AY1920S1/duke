package duke.task;

public class DoAfterTasks extends Task {

    private String after;

    /**
     * Initialises a DoAfterTasks.
     * A DoAfterTasks consist of the task itself and the prerequisite task.
     *
     * @param item name of the task.
     * @param after name of the prerequisite task.
     */
    public DoAfterTasks(String item, String after) {
        super(item);
        this.after = after;
    }

    /**
     * Prints a DoAfterTasks as a string to the user in a specific format.
     *
     * @return DoAfterTasks.
     */
    @Override
    // overrides toString method in Task
    public String toString() {
        if (isDone) {
            return "[DA][" + "/" + "] " + super.toString() + " (after: " + after + ")";
        } else {
            return "[DA][" + "x" + "] " + super.toString() + " (after: " + after + ")";
        }
    }

    /**
     * Saves a DoAfterTasks as a string to the file in a specific format.
     *
     * @return DoAfterTasks.
     */
    @Override
    // overrides saveTask method in Task
    public String saveTask() {
        if (isDone) {
            return "DA" + " | " + "1" + " | " + super.toString() + " | " + after + "\n";
        } else {
            return "DA" + " | " + "0" + " | " + super.toString() + " | " + after + "\n";
        }
    }

}

package duke.tasks;

/**
 * Represents the task with to do specifications.
 */
public class ToDo extends Task {

    /**
     * Create an initial To Do task that initializes
     * to incomplete by default.
     * @param description a description given for the task
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Creates a To Do task mainly for storage purposes.
     * This constructor keeps the state of the task whether
     * is it done or incomplete.
     *
     * @param description a description given for the task
     * @param doner the completion status of the task
     *              (Completed is 1, incomplete is 2)
     */
    public ToDo(String description, int doner) {
        super(description);
        if (doner == 1) {
            super.completed();
        }
    }

    /**
     * Returns a string that represents the To Do that will be stored in
     * the format got storage and retrieval in a .txt file
     *
     * @return the string of the task that will be saved in the .txt file
     */
    @Override
    public String save() {
        int a = 0;
        if (super.isDone) {
            a = 1;
        }
        return "T|" + a + "|" + super.description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
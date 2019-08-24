package tasks;

/**
 * ToDo is a class that represents tasks that are to be done.
 * A ToDo task only contains a description as well as a state of completion.
 */
public class ToDo extends Task {

    /**
     * ToDo constructor that takes in a description as well as a boolean
     * value representing the state of completion of the task as arguments.
     *
     * @param description String containing the description of the task.
     * @param isDone boolean storing the task's state of completion.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string that is of the appropriate format
     * to be saved to the file. This formatting ensures that
     * the task information can be read accurately again in the future.
     * ToDo objects are to be stored in this format:
     * T | isDone | description
     *
     * @return String format of the task to be saved to the file.
     */
    @Override
    public String fileString() {
        return "T" + super.fileString();
    }
}
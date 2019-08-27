package slave.task;

/**
 * Class to represent a to-do task
 */
public class ToDo extends Task {

    /**
     * Constructor for to-do task
     * @param description description of to-do task
     * @param id id of to-do task
     */
    public ToDo(String description, int id) {
        super(description, id);
        this.type = TaskType.TODO;
    }

    /**
     * Converts to-do task to an appropriate String representation
     * @return formatted string of to-do task to be printed
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
package duke.task;

/**
 * A simple sub-class of Task in which there is
 * no given time period or deadline by which
 * it should be completed.
 */
public class Todo extends Task {

    /**
     * Basic constructor of the Todo sub-class.
     * Takes in description of the task only.
     * @param description details of the task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the Todo object and
     * its various details.
     *
     * @return a string representation of the Todo object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

public class Task {
    protected String description;

    /**
     * Basic constructor for the Task
     * class that only takes in one string
     * for description.
     *
     * @param description description/name of Task
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Overridden toString() method for the
     * Task class.
     */
    @Override
    public String toString() {
        return description;
    }
}
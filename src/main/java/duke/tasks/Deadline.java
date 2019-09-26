package duke.tasks;

/**
 * Represents a deadline task in the application.
 * A deadline provides the getter methods to its date.
 */
public class Deadline extends Task {

    private String date;

    /**
     * Initialises an deadline task with the description and date and time of the deadline task.
     *
     * @param description Deadline description
     * @param date Date description
     */
    public Deadline(String description, String date) {
        super(description, TaskType.DEADLINE_TASK);
        this.date = date;

        assert date != null;
    }

    /**
     * Returns a string containing the date and time of a deadline task
     *
     * @return String containing the date and time of a deadline task.
     */
    public String getDate() {
        assert date != null;
        return this.date;
    }

    /**
     * Returns A string that includes the task type, description and date of the deadline task.
     *
     * @return String that includes the task type, description and date of the deadline task.
     */
    @Override
    public String toString() {
        return String.format("[D][%s] %s(by: %s)", getStatusIcon(),
                getDescription(), getDate());
    }
}
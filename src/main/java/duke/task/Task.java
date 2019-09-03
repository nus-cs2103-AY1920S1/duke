package duke.task;

/**
 * Class for tasks.
 */
public class Task {
    /**
     * Description of task.
     */
    protected String description;
    /**
     * Done status.
     */
    protected boolean isDone;

    /**
     * Default constructor. Sets isDone to false.
     */
    public Task() {
        isDone = false;
    }

    /**
     * Constructor of Task. Sets description and sets isDone to false.
     *
     * @param description description of task
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    /**
     * Returns status of isDone.
     *
     * @return "Y" or "N" depending on isDone
     */
    protected String getStatusIcon() {
        return (isDone ? "Y" : "N");
    }

    /**
     * Returns description of task.
     *
     * @return description of task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set isDone to true.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Returns String in text file format.
     *
     * @return a String to write on text file
     */
    public String toFile() {
        return getStatusIcon() + "|" + description;
    }

    /**
     * Returns String to output on terminal.
     *
     * @return a String to output on terminal
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}

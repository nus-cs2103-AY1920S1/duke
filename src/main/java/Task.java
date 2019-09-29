import java.time.format.DateTimeFormatter;

/**
 * Parent class for task.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;
    protected static final DateTimeFormatter OUT_DTF = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    /**
     * Constructor for Task.
     *
     * @param description The task description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor for Task.
     *
     * @param description The task description
     * @param done        representing if the task is done
     */
    public Task(String description, String done) {
        this.description = description;
        if (done.equals("1")) {
            this.isDone = true;
        } else {
            this.isDone = false;
        }
    }

    /**
     * Method to return tick of X symbols.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Method to set a task done.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Method to get the type of task.
     *
     * @return A string representing the type of the task
     */
    public String getType() {
        return this.type;
    }

    /**
     * Method to return a string representing if the task is done.
     *
     * @return A string 0 or 1 representing if the task is done
     */
    public String getIsDone() {
        if (isDone) {
            return "1";
        } else {
            return "0";
        }
    }

    /**
     * Method to return the description of the task.
     *
     * @return A string representing the description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Override the toString method.
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}
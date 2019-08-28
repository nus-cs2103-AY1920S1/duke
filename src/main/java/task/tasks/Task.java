package task.tasks;

import java.io.Serializable;
import java.util.Optional;

/***
 * <p>
 * Task interface to create new task types.
 * Serializable to be written to storage.
 * </p>
 */
public abstract class Task implements Serializable {
    private static final long serialVersionUID = 6529685098267757690L;
    private String description;
    private boolean done;
    private TaskKeyword keyword;

    /***
     * <p>
     * Task default constructor.
     * </p>
     * @param description each tasks has a description.
     * @param keyword each task has a keyword to be input by the user in order to perform related operations.
     */
    public Task(String description, TaskKeyword keyword) {
        this.description = description;
        this.keyword = keyword;
        done = false;
    }

    /***
     * <p>
     * Returns string code to represent a task type e.g. Event -> E
     * </p>
     * @return task string code.
     */
    protected abstract String getTaskStringCode();

    /***
     * <p>
     * Returns any extra details to be appended to the task message.
     * </p>
     * @return task description message.
     */
    protected abstract Optional<String> getTaskExtraDetails();

    private String getStatusIcon() {
        return done ? "\u2713" : "\u2718";
    }

    /***
     * <p>
     * Sets task to done.
     * </p>
     * @param done task done state.
     */
    public void setDone(boolean done) {
        this.done = done;
    }

    public String getDescription() {
        return description;
    }

    /***
     * <p>
     * Returns task message.
     * </p>
     * @return task message that describes the tasks state and details.
     */
    public final String getTaskMessage() {
        String message = String.format("[%s][%s] %s",
                getTaskStringCode(),
                getStatusIcon(),
                description);

        return getTaskExtraDetails()
                .map(details -> String.format("%s (%s)", message, details))
                .orElse(message);
    }
}

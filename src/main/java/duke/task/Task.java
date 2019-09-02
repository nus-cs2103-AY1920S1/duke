package duke.task;

/** Represents a task.
 * The  abstract 'Task' class supports operator (i) toString that
 * returns the string representation of task.
 */
public abstract class Task {
    /** Description of task */
    private String description;

    /** Whether task is done */
    protected boolean isDone;


    /** Initialises a new instance od a task.
     * Used for when user inputs event task directly to the chatbot.
     *
     * @param description Description of task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

   /* public Task(boolean isDone, String description) {
        this.isDone = isDone;
        this.description = description;
    } */

    /** Initialises a new instance oft task.
     * Used for loading the tasks form text file to TaskList.
     *
     * @param isDone 0 or 1 representation of whether a task is done.
     * @param description Description of task.
     */
    public Task(String isDone, String description) {
        int a = Integer.parseInt(isDone.trim());
        this.isDone = a == 0 ? true : false;
        this.description = description;
    }

    /** Returns a string of task description.
     *
     * @return Description of task.
     */
    public String getDescription() {
        return this.description;
    }

    /** Returns a string representing whether task is done.
     *
     *
     * @return STring representation of whether task is done.
     * v for when task is done and x for when task is not done.
     */
    public String getStatusIcon() {
        return (this.isDone ? "v" : "x"); // "\u2713" : "\u2718"); //return tick or X symbols
    }

    /** Marks task as done.*/
    public void markAsDone() {
        isDone = true;
    }

    /** Returns a string represenation of task */
    public abstract String toString();
}


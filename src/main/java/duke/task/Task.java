package duke.task;

/**
 * Represents a task.
 * The  abstract 'Task' class supports operator (i) toString that
 * returns the string representation of task.
 */
public abstract class Task {
    /**
     * Description of task.
     */
    private String description;

    /**
     * Whether task is done.
     */
    protected boolean isDone;

    static final int NOT_DONE = 1;
    static final int DONE = 0;


    /**
     * Initialises a new instance od a task.
     * Used for when user inputs event task directly to the chatbot.
     *
     * @param description Description of task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }


    /**
     * Initialises a new instance oft task.
     * Used for loading the tasks form text file to TaskList.
     *
     * @param isDone      0 or 1 representation of whether a task is done.
     * @param description Description of task.
     */
    public Task(String isDone, String description) {
        int a = Integer.parseInt(isDone.trim());
        //Assert that a is either 1 or 0
        assert (a == NOT_DONE) || (a == DONE) : "Integer value of whether task is done is not parsed out properly";

        this.isDone = a == DONE;
        this.description = description;
    }

    /**
     * Returns a string of task description.
     *
     * @return Description of task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns a string representing whether task is done.
     * The value v for when task is done and x for when task is not done.
     *
     * @return Striing representation of whether task is done.
     */
    public String getStatusIcon() {
        return (this.isDone ? "v" : "x"); // "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Marks task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Returns a string represenation of task.
     */
    public abstract String toString();
}


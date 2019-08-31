package duke.task;
/**
 * Task class
 */
public class Task {
    protected boolean completed;
    protected int index;
    protected String name;
    protected static int count = 0;
  
    /**
     * Constructor for Task object
     * @param n name of task
     */
    public Task(String n) {
        count++;
        this.name = n;
        this.index = count;
        completed = false;
    }
  
    /**
     * Constructor for Task object when loading from history
     * @param n name of task
     * @param completed indicates if task is completed
     */
    public Task(String n, boolean completed) {
        count++;
        this.name = n;
        this.index = count;
        this.completed = completed;
    }

    /**
     * Checks if a task is completed
     * @return true if task is completed, false otherwise
     */
    public boolean isCompleted() {
        return this.completed;
    }

    /**
     * Returns name of task
     * @return name of task
     */
    public String getName() {
        return this.name;
    }
  
    /**
     * Returns the index of the task relative to the tasklist
     * @return index of task
     */
    public int getIndex() {
        return this.index;
    }

    /**
     * Completes the task
     */
    public void complete() {
        this.completed = true;
    }
}

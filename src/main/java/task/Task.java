package task;

/**
 * Abstract class for all tasks, with <param>name</param> as the actual task,
 * <param>completed</param> as the state of the tasks completion and
 * <param>type</param> as the different type of tasks
 */
public abstract class Task {
    String name;
    boolean completed = false;
    String type = null;

    /**
     *
     */

    public Task(String taskInput) {
        name = taskInput;
    }

    /**
     *
     */

    public String getName() {
        return name;
    }

    /**
     *
     */
    public String getType() { return type;}

    /**
     *
     */
    public boolean getDoneStatus() { return completed;}

    /**
     * Converts the task completion state to done (true)
     *
     * @return Task itself, now that it is completed
     */

    public Task taskComplete() {
        completed = true;
        return this;
    }

    /**
     *
     */

    @Override
    public String toString() {
        if(completed) {
            return "[" + type  + "]" + "[\u2713] " + name;
        } else {
            return "[" + type + "]" + "[\u2718] " + name;
        }

    }


}

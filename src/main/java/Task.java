/**
 * An Abstract class which represent the basic of a task. A task at least needs a name.
 */
abstract class Task {
    boolean completed = false;
    String task;

    /**
     * Constructs a task.
     * @param task The name of the task.
     */
    public Task(String task) {
        this.task = task;
    }

    /**
     * Constructs a task that has or may not be completed.
     * @param task The name of the task.
     * @param isCompleted The state of the task's completion.
     */
    public Task(String task, boolean isCompleted) {
        this.task = task;
        this.completed = isCompleted;
    }

    /**
     * Completes the task.
     */
    public void complete() {
        this.completed = true;
    }

    /**
     * returns a string that is used to store it in the save file.
     * @return a string specifically formatted for storage.
     */
    @Override
    public String toString() {
        if (completed) {
            return "[✓] " + this.task;
        } else {
            return "[✗] " + this.task;
        }
    }

    abstract String toFileFormat();
}

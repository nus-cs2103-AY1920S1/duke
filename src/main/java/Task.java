/**
 * An Abstract class which represent the basic of a task. A task at least needs a name.
 */
abstract class Task {
    boolean completed = false;
    protected String task;
    protected String type = "";


    /**
     * Constructs a task.
     *
     * @param task The name of the task.
     */
    public Task(String task, String type) {
        this.task = task;
        this.type = type;
    }

    /**
     * Constructs a task that has or may not be completed.
     *
     * @param task        The name of the task.
     * @param isCompleted The state of the task's completion.
     */
    public Task(String task, boolean isCompleted, String type) {
        this.task = task;
        this.completed = isCompleted;
        this.type = type;
    }

    /**
     * Completes the task.
     */
    public void complete() {
        this.completed = true;
    }

    /**
     * returns a string that is used to store it in the save file.
     *
     * @return a string specifically formatted for storage.
     */
    @Override
    public String toString() {
        String output = "[" + this.type + "]";
        if (completed) {
            output += "[" + Character.toString(10003) + "] " + this.task;
        } else {
            output += "[" + Character.toString(10005) + "] " + this.task;
        }
        return output;
        /*
        ✓ "\u2713" int 100003
        ✗ "\u2715" int 100005
         */
    }


    abstract String toFileFormat();
}

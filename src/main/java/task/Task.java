package task;

/**
 * Abstract class for all tasks, with name as the actual task.
 * completed as the state of the tasks completion and
 * type as the different type of tasks
 */

public abstract class Task {
    String name;
    boolean completed = false;
    String type = null;
    String tick = "\u2713";
    String cross = "\u2718";

    /**
     * Constructor assigns the input Task.
     *
     * @param taskInput is the userInput task
     */

    public Task(String taskInput) {
        name = taskInput;
    }


    public String getName() {
        return name;
    }


    public String getType() {
        return type;
    }

    public boolean getDoneStatus() {
        return completed;
    }

    /**
     * Converts the task completion state to done (true).
     *
     * @return Task itself, now that it is completed
     */

    public Task taskComplete() {
        completed = true;
        return this;
    }


    @Override
    public String toString() {
        if (completed) {
            return "[" + type  + "]" + "[" + tick + "] " + name;
        } else {
            return "[" + type + "]" + "[" + cross + "] " + name;
        }

    }


}

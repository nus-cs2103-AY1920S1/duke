package duke.tasks;

/**
 * Represents a Task
 */

public class Task {

    String taskName;
    boolean isDone;

    /**
     * Creates a task object
     * @param taskName name of the task
     * @param isDone whether the deadline task has been completed
     */

    public Task(String taskName, boolean isDone){
        this.taskName = taskName;
        this.isDone = isDone;
    }

    /**
     * Sets isDone boolean
     * @param done boolean to set the isDone boolean to
     */

    public void setDone(boolean done){
        isDone = done;
    }

    /**
     * Returns a string to represent the task in the storage file
     * @return Task String in file form
     */

    public String toFile(){
        String mark = isDone ? "1" : "0";
        return "T | " + mark + " |" + taskName;
    }

    /**
     * Returns Task in string form to be printed
     * @return Task String in print form
     */

    @Override
    public String toString(){
        String mark = isDone ? "✓" : "✗";
        return "[T][" + mark + "]" + taskName;
    }
}

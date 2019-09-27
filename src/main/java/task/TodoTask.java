package task;


/**
 * Represents a TodoTask without a timing
 */

public class TodoTask extends Task {

    /**
     * Sets the initial params for the task and sets type to T for deadline
     *
     * @param taskInput  String of the actual task
     */

    public TodoTask(String taskInput) {
        super(taskInput);
        type = "T";

    }

    /**
     * Overloaded constructor for reading from file
     *
     * @param i  String of the actual task
     * @param c is the completion status
     */

    public TodoTask(String i, boolean c) {
        super(i);
        type = "T";
        completed= c;
    }

}

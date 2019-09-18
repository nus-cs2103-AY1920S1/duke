package task;

/**
 * Represents a TodoTask without a timing
 */

public class TodoTask extends Task {

    /**
     * Sets the initial params for the task and sets type to T for deadline
     *
     * @param inputTask  String of the actual task
     * @param complete Boolean is initially set to false
     */

    public TodoTask(String taskInput, boolean complete) {
        super(taskInput,complete);
        type = "T";

    }

}

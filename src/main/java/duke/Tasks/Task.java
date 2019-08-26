package duke.Tasks;

import duke.DukeException;

/**
 * This is abstract class for all tasks. Each task contains a taskName and whether it is finished or not.
 * They can return their own information as a String to the user or to write into a file.
 */
public abstract class Task {

    /** Indicate whether this task is finished or not */
    private boolean finished;

    /** The task name */
    private String taskName;

    /** The total number of tasks existing */
    private static int taskNumbers = 0;

    /**
     * General constructor, initialize a task, the finished instance is false and set the taskName.
     * @param taskName The task name.
     * @throws DukeException If the task name is empty.
     */
    public Task(String taskName) throws DukeException {
        if (taskName.equals("")) {
            throw new DukeException("Input task name cannot be empty.");
        }
        this.taskName = taskName;
        this.finished = false;
        taskNumbers++;
    }

    /**
     * This method returns the information of the task FOR THE USER to see.
     * Output of this method is usually handled by Ui class.
     * @return The information of the task, in form [type][finished] task name. For example, [T][X] Eat dinner.
     */
    public abstract String taskInfo();

    /**
     * This method returns the information of the task FOR SAVING INTO A FILE.
     * Output of this method is usually handled by the task list.
     * @return The information of the task, in form type|finished|task name. For example, T|0|Eat dinner.
     */
    public abstract String recordInfo();

    /**
     * This method returns the name of the task. Only be passed around inside the package.
     * @return The name of the task.
     */
    protected String getName() {
        return taskName;
    }

    /**
     * Get the total number of the tasks existing, which is the number of tasks in the task list.
     * @return
     */
    public static int getTotalNumber() {
        return taskNumbers;
    }

    /**
     * Minus the total number by 1 after each deletion of a task.
     */
    public static void reduceTotalNumber() {
        taskNumbers--;
    }

    /**
     * Change the finished instance to true.
     */
    public void setAsFinish() {
        this.finished = true;
    }

    /**
     * Show if the task is finished or not. Only be passed around inside the package.
     * @return
     */
    protected boolean isFinished() {
        return finished;
    }
}

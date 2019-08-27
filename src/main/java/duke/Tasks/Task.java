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
     * Decide whether the give string is contained in the task name or not.
     * @param s The target string.
     * @return boolean, whether s is contained in the task name or not.
     */
    public boolean match(String s) {
        return taskName.contains(s);
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

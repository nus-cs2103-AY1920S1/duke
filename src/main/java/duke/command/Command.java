package duke.command;

import duke.task.TaskList;

/**
 * An abstract class for commands.
 */
public abstract class Command {
    /**
     * Executes the command on a TaskList.
     *
     * @param tasks The TaskList to execute the command on.
     * @return A string to describe the execution of the command.
     */
    public abstract String execute(TaskList tasks);

    protected String createTotalNumOfTaskMsg(TaskList tasks) {
        return "Now you have " + tasks.getSizeOfTaskList() + " tasks in the list.";
    }
}

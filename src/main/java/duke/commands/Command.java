package duke.commands;

import duke.data.TaskList;

/**
 * Provides a framework for commands to be built upon.
 * @author Lim Yong Shen, Kevin
 */
public abstract class Command {

    protected TaskList tasks;

    /**
     * Executes this command and returns its command result.
     * @return This command's command result.
     */
    public abstract CommandResult execute();

    /**
     * Sets this command to execute on the specified task list.
     * @param tasks The specified task list.
     */
    public void setTaskListToExecuteOn(TaskList tasks) {
        this.tasks = tasks;
    }

}

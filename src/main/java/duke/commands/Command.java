package duke.commands;

import duke.data.TaskList;

/**
 * Provides a framework for commands to be built upon.
 * @author Lim Yong Shen, Kevin
 */
public abstract class Command {

    protected TaskList tasks;

    /**
     * Executes this Command and returns its CommandResult.
     * @return This Command's CommandResult.
     */
    public abstract CommandResult execute();

    /**
     * Returns true if this Command is an ExitCommand.
     * @return true if this Command is an ExitCommand.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Sets this command's TaskList to execute on to the specified TaskList.
     * @param tasks The specified TaskList.
     */
    public void setTaskListToExecuteOn(TaskList tasks) {
        this.tasks = tasks;
    }

}

package command;

import run.Storage;
import run.TaskList;
import run.Ui;

/**
 * Abstract class for commands that results from user input that may
 * modify or retrieve information from the task list in various ways
 */
public abstract class Command {

    /**
     *
     * @param tasks
     * @param ui
     * @param storage
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
    /** Checks if this command is an exit ("bye") command
     * @return boolean that checks if this is exit ("bye") command
     */
    public abstract boolean isExit();
}
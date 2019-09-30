package command;

import task.TaskList;
import main.Ui;
import main.Storage;
import task.InsufficientTaskArgumentException;

public interface Command {
    /**
     * execute performs the command in the gui.Duke app.
     * @param tasks TaskList that contains the list of tasks that is tracked.
     * @param ui Ui of the app.
     * @param storage Storage is the class that manages file reading and file writing of the data passed into the app.
     * @throws InsufficientTaskArgumentException exception thrown when command does not have enough arguments.
     */
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) throws InsufficientTaskArgumentException;

    /**
     * isExit checks if the command is an exit command.
     * @return boolean whether if the command is an exit command.
     */
    public boolean isExit();

    /**
     * toString() returns the command and its arguments if there is any.
     * @return String.
     */
    public String toString();
}

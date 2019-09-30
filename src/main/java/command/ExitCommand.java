package command;

import main.Storage;
import main.Ui;
import task.TaskList;
import task.InsufficientTaskArgumentException;

public class ExitCommand implements Command {

    /**
     * Constructor for "exit" commands.
     */
    public ExitCommand() {
    }

    /**
     * isExit checks if the command is an exit command.
     * @return boolean whether if the command is an exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * execute performs the command in the gui.Duke app.
     * @param tasks TaskList that contains the list of tasks that is tracked.
     * @param ui Ui of the app.
     * @param storage Storage is the class that manages file reading and file writing of the data passed into the app.
     * @throws InsufficientTaskArgumentException exception thrown when command does not have enough arguments.
     */
    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) throws InsufficientTaskArgumentException {
        ui.nextLine("See you later! 8)");
        storage.updateTasks(tasks);
        return tasks;
    }

    /**
     * toString() returns the command and its arguments if there is any.
     * @return String.
     */
    @Override
    public String toString() {
        return "Command: exit";
    }
}

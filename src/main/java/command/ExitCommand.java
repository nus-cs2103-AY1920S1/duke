package command;
import main.Storage;
import main.Ui;
import main.TaskList;
import task.InsufficientTaskArgumentException;
import task.InvalidTaskException;

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
     * execute performs the command in the Duke app.
     * @param tasks TaskList that contains the list of tasks that is tracked.
     * @param ui Ui of the app.
     * @param storage Storage is the class that manages file reading and file writing of the data passed into the app.
     * @throws InsufficientTaskArgumentException exception thrown when command does not have enough arguments.
     * @throws InvalidTaskException exception thrown when task is invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidTaskException, InsufficientTaskArgumentException {
        return ;
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

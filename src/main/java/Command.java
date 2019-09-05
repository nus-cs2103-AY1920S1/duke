import java.io.IOException;

/**
 * This is an abstract class for command inputs.
 * @author Choong Yong Xin
 */

abstract class Command {
    String commandDesc;

    Command(String commandDesc) {
        this.commandDesc = commandDesc;
    }

    /**
     * Returns a boolean to indicate whether the command is an exit command.
     *
     * @return true if the command is an exit command, false otherwise.
     */
    abstract boolean isExit();

    /**
     * Returns a string response by Quack when the command is executed.
     *
     * @param tasks TaskList containing the tasks.
     * @param storage Storage to save the tasks.
     * @return string to be displayed
     */
    abstract String execute(TaskList tasks, Storage storage) throws DukeException, IOException;
}

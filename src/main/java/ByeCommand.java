/**
 * This is a class for the exit command "bye".
 * @author Choong Yong Xin
 */

public class ByeCommand extends Command {

    ByeCommand(String commandDesc) {
        super(commandDesc);
    }

    /**
     * Returns a boolean to indicate whether the command is an exit command.
     *
     * @return true as the command is an exit command.
     */
    boolean isExit() {
        return true;
    }

    /**
     * Returns a string response by Quack when the command is executed.
     *
     * @param tasks TaskList containing the tasks.
     * @param storage Storage to save the tasks.
     * @return string to be displayed.
     */
    @Override
    String execute(TaskList tasks, Storage storage) {
        return "Bye. Hope to see you again soon!";
    }
}

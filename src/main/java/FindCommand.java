import java.io.IOException;

/**
 * This is a class for command to find tasks.
 * @author Choong Yong Xin
 */

public class FindCommand extends Command {

    FindCommand(String commandDesc) {
        super(commandDesc);
    }

    /**
     * Returns a boolean to indicate whether the command is an exit command.
     *
     * @return false as command is not an exit command.
     */
    boolean isExit() {
        return false;
    }

    /**
     * Returns a string response by Quack when the command is executed.
     *
     * @param tasks TaskList containing the tasks.
     * @param storage Storage to save the tasks.
     * @return string to be displayed
     */
    @Override
    String execute(TaskList tasks, Storage storage) throws DukeException, IOException {
        try {
            /*Check if description is empty (does not check when user input
              multiple spaces as the description.
            */
            if (!commandDesc.substring(5).equals((""))) {
                String output = tasks.findTasks(commandDesc.substring(5));
                return output;
            } else {
                throw new EmptyDescDukeException("find");
            }
        } catch (IndexOutOfBoundsException err) {
            throw new EmptyDescDukeException("find");
        }
    }
}

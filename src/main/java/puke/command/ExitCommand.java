package puke.command;

import puke.storage.Storage;
import puke.task.TaskList;

/**
 * Class representing a command to exit Duke.
 */
public class ExitCommand extends Command {
    /**
     * Executes this command on the given parameters.
     *
     * @see Command#execute
     */
    public void execute(TaskList tl, ResponseStrings respStrings, Storage storage) {
        respStrings.add("Bye!");
    }
}

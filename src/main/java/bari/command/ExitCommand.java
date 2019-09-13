package bari.command;

import bari.storage.Storage;
import bari.task.TaskList;

/**
 * Class representing a command to exit the program.
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

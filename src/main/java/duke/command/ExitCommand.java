package duke.command;

import duke.DukeException;
import duke.common.Message;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {
    /**
     * Executes exit program.
     *
     * @param taskList list of tasks.
     * @param storage local storage of data.
     * @return exit message.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        return Message.MESSAGE_BYE;
    }
}

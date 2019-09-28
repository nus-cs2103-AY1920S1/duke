package duke.command;

import duke.DukeException;
import duke.common.MessageUtils;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {
    /**
     * Executes exit program.
     *
     * @param dukeResponse response from Duke to user.
     * @param taskList list of tasks.
     * @param storage local storage of data.
     */
    @Override
    public void execute(DukeResponse dukeResponse, TaskList taskList, Storage storage) throws DukeException {
        dukeResponse.add(MessageUtils.MESSAGE_BYE);
    }
}

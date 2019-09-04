package dukegui.command;

import duke.module.AutoResponse;
import duke.module.Storage;
import duke.module.TaskList;

/**
 * Represents the "exit" command supported by Duke.
 */
public class ExitCommand extends Command {

    /**
     * Returns the response to command "bye."
     *
     * @param taskList List of tasks to manage.
     * @param storage Storage to save any changes if necessary.
     */
    @Override
    public String getResponse(TaskList taskList, Storage storage) {
        return AutoResponse.DUKE_BYE;
    }

    /**
     * Returns true.
     *
     * @return True.
     */
    @Override
    public boolean isExit() {
        return true;
    }

}

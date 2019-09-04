package duke.command;

import duke.module.Storage;
import duke.module.TaskList;
import duke.module.Ui;

/**
 * Represents the "exit" command supported by Duke.
 */
public class ExitCommand extends Command {

    /**
     * Quits Duke.
     *
     * @param taskList List of tasks to manage.
     * @param ui UI to show result to user.
     * @param storage Storage to save any changes if necessary.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.bye();
    }

    /**
     * Returns the response to command "bye."
     *
     * @param taskList List of tasks to manage.
     * @param storage Storage to save any changes if necessary.
     */
    @Override
    public String getResponse(TaskList taskList, Storage storage) {
        // TODO: use package
        return "Bye. Hope to see you again soon!";
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

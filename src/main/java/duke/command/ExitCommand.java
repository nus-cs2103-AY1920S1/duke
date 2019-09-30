package duke.command;

import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;
import duke.util.UiMessage;

/**
 * Exits the application.
 */
public class ExitCommand implements Command {

    /**
     * Exits the application.
     * @param tasks List of tasks.
     * @param ui UI to display to the user.
     * @param storage Object that handles storage of task list to disk.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // todo: in future, "lock down" ui and actually exit Duke when this command is called?
        // todo: call this command when the "close" button is pressed by user
        storage.save(tasks);
        ui.showMessage(UiMessage.GOODBYE);
    }

    /**
     * Returns boolean indicating if command entered was "exit", true in this case.
     * @return boolean indicating if command entered was "exit".
     */
    @Override
    public boolean isExit() {
        return true;
    }
}

package duke.command;

import duke.exception.DukeException;
import duke.ui.Ui;

import duke.task.TaskList;

import duke.storage.Storage;

public class DoneCommand extends Command {

    String command;

    /**
     * Constructor to create an DoneCommand object.
     *
     * @param command User's command.
     */
    public DoneCommand(String command) {
        this.command = command;
    }

    /**
     * Get the TaskList object to mark the task in the list as done.
     *
     * @param taskList TaskList object containing the current tasks list.
     * @param ui The Ui object we are currently using.
     * @param storage The Storage object we are currently using.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        assert taskList != null || ui != null || storage != null :
                "TaskList, Ui and Storage objects cannot be null";

        String response = "";
        try {
            response = taskList.doneTask(command, ui);
        } catch (DukeException e) {
            response = e.getMessage();
        } finally {
            return response;
        }
    }

    /**
     * Checks if the command is an ExitCommand.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

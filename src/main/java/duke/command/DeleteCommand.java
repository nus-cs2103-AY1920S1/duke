package duke.command;

import duke.ui.Ui;

import duke.task.TaskList;

import duke.storage.Storage;

public class DeleteCommand extends Command {

    String command;

    /**
     * Constructor to create an DeleteCommand object.
     * @param command User's command.
     */
    public DeleteCommand(String command) {
        this.command = command;
    }

    /**
     * Get the TaskList object to delete the task in the list.
     * @param taskList TaskList object containing the current tasks list.
     * @param ui The Ui object we are currently using.
     * @param storage The Storage object we are currently using.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.deleteTask(command, ui);
    }

    /**
     * Checks if the command is an ExitCommand.
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

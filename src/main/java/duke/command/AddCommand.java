package duke.command;

import duke.ui.Ui;

import duke.task.TaskList;

import duke.storage.Storage;

public class AddCommand extends Command {

    String command;

    /**
     * Constructor to create an AddCommand object.
     *
     * @param command User's command.
     */
    public AddCommand(String command) {
        this.command = command;
    }

    /**
     * Get the TaskList object to add the task into the list.
     *
     * @param taskList TaskList object containing the current tasks list.
     * @param ui The Ui object we are currently using.
     * @param storage The Storage object we are currently using.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(command, ui);
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

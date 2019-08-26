package duke.command;

import duke.ui.Ui;

import duke.task.TaskList;

import duke.storage.Storage;

public class ExitCommand extends Command {

    /**
     * Exit the chatbot. Get Ui to show the exit message and Storage to update data.
     *
     * @param taskList TaskList object containing the current tasks list.
     * @param ui The Ui object we are currently using.
     * @param storage The Storage object we are currently using.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showExit();
        storage.updateData(taskList.getTasks());
    }

    /**
     * Checks if the command is an ExitCommand.
     *
     * @return True.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}

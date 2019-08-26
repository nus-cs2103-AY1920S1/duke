package duke.command;

import duke.ui.Ui;

import duke.task.TaskList;

import duke.storage.Storage;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showExit();
        storage.updateData(taskList.getTasks());
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

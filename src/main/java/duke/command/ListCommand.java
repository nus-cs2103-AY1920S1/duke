package duke.command;

import duke.ui.Ui;

import duke.task.TaskList;

import duke.storage.Storage;

public class ListCommand extends Command {

    public ListCommand() {
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printTasks(taskList.getTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

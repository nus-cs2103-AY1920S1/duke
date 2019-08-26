package duke.command;

import duke.ui.Ui;

import duke.task.TaskList;

import duke.storage.Storage;

public class AddCommand extends Command {

    String command;

    public AddCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(command, ui);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

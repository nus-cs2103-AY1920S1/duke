package duke.command;

import duke.ui.Ui;

import duke.task.TaskList;

import duke.storage.Storage;

public class DoneCommand extends Command {

    String command;

    public DoneCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.doneTask(command, ui);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

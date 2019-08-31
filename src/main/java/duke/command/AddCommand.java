package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public abstract class AddCommand implements Command {

    protected Task task;

    protected String line;

    public AddCommand(String line) {
        this.line = line;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(task);
        ui.showAddInformation(task.toString(), tasks.size());
    }

    public boolean isExit() {
        return false;
    }

}

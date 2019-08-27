package duke.command;

import duke.task.*;
import duke.exception.*;
import duke.storage.Storage;
import duke.ui.Ui;

public class AddCommand extends Command {
    protected Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(task);
        ui.printAddTaskMessage(task.toString(), tasks.size());
    }
}
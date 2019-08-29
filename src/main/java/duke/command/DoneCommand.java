package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class DoneCommand extends Command {
    protected int doneTask;

    public DoneCommand(int doneTask) {
        super();
        this.doneTask = doneTask;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.markAsDone(this.doneTask);
        ui.printDone(tasks.get(this.doneTask).toString());
    }
}
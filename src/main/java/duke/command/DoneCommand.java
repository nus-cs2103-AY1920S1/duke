package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class DoneCommand extends Command {
    private int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task t = tasks.setDone(index);
        ui.show( "Nice! I've marked this task as done: \n"
                + "        " + t);
        storage.updateSaveFile(tasks);
    }
}

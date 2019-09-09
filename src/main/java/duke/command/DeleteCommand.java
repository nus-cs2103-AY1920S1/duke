package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class DeleteCommand extends Command{
    public int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task t = tasks.delete(index);
        ui.show("Noted. I've removed this task: \n"
                + "       " + t + "       Now you have "
                + tasks.size() + " tasks in the list.");
        storage.updateSaveFile(tasks);
    }
}

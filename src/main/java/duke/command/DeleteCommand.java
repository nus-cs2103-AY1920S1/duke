package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;
import duke.task.Task;

public class DeleteCommand extends Command{
    public int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     *Removes task of this.index from TaskList. Updates savefile with new list
     * Prints out new list
     * @param tasks   duke.TaskList
     * @param ui      duke.ui.Ui
     * @param storage duke.Storage
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task t = tasks.delete(index);
        ui.show("Noted. I've removed this task: \n"
                + "        " + t + "\nNow you have "
                + tasks.size() + " tasks in the list.");
        storage.updateSaveFile(tasks);
    }
}

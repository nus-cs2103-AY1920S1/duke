package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;
import duke.task.Task;

public class DoneCommand extends Command {
    private int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Sets done indicator of task in TaskList from 0 to 1. Updates save file with new list
     * Prints out new list
     * @param tasks   duke.TaskList
     * @param ui      duke.ui.Ui
     * @param storage duke.Storage
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task t = tasks.setDone(index);
        ui.show( "Nice! I've marked this task as done: \n"
                + "        " + t);
        storage.updateSaveFile(tasks);
    }
}

package duke.command;

import duke.dukeexception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command {

    private int toDelete;

    public DeleteCommand(int toDelete) {
        this.toDelete = toDelete;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            if (toDelete >= taskList.size() || toDelete < 0) {
                throw new DukeException("☹ OOPS! duke.task.Task " + (toDelete + 1) + " doesn't exist!");
            } else {
                Task curr = taskList.get(toDelete);
                ui.printDeleted(curr);
                taskList.deleteTask(toDelete);
                ui.printNumTasks();
                storage.setChangedTrue();
            }
        } catch (DukeException de) {
            ui.printTaskError(de.getMessage());
        }
    }

    public boolean isExit() {
        return false;
    }
}
